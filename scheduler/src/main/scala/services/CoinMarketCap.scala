package services

import java.util.Date

import converting.DateUtils
import javax.inject.Inject
import models.CurrencyInfo
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

class CoinMarketCap @Inject()(wsClient: WSClient) extends CrawlingCurrencyDataService {
  override def crawlCurrencyData(currency: String, from: Date, to: Date): Future[Seq[CurrencyInfo]] = {

    def parseData(wsResponse: WSResponse): Seq[CurrencyInfo] = {
      type MarketCapData = Seq[Seq[Double]]
      val MARKET_CAP_BY_AVAILABLE_SUPPLY = "market_cap_by_available_supply"
      val PRICE_USD = "price_usd"
      val VOLUME_USD = "volume_usd"
      val NA_ID = -1

      val json: JsValue = Json.parse(wsResponse.body)
      (json(MARKET_CAP_BY_AVAILABLE_SUPPLY).as[MarketCapData]
        .toStream
        .map(row => row.head -> row.last) ++
        json(PRICE_USD).as[MarketCapData]
          .toStream
          .map(row => row.head -> row.last) ++
        json(VOLUME_USD).as[MarketCapData]
          .toStream
          .map(row => row.head -> row.last))
        .groupBy(_._1)
        .mapValues(_.map(_._2))
        .map {
          case (key, value) => CurrencyInfo(
            key.toLong,
            NA_ID,
            value.head.toLong,
            value(1),
            value(2).toLong)
        }.toSeq
    }

    val url =
      s""""https://graphs2.coinmarketcap.com/currencies/
         |$currency/
         |${DateUtils.convertDateToEpoch(from)}/
         |${DateUtils.convertDateToEpoch(to)}/""".stripMargin.replaceAll("\n", " ")
    wsClient.url("http://www.google.com").get().map(parseData)
  }
}
