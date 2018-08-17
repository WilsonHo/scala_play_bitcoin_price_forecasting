package services

import java.util.Date

import javax.inject.Inject
import models.CurrencyInfo
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

class CoinMarketCap @Inject()(wsClient: WSClient) extends CrawlingCurrencyDataService {
  override def crawlCurrencyData(currency: String, from: Date, to: Date): Future[Seq[CurrencyInfo]] = ???
//  {
    def parseData(wsResponse: WSResponse): Seq[CurrencyInfo] = ???
//    {
//      val jString =
//        """{
//                      market_cap_by_available_supply: [
//                      [
//                      1484588368000,
//                      13367198196
//                      ],
//                      [
//                      1484588667000,
//                      13364395700
//                      ]
//                      ],
//                      price_btc: [
//                      [
//                      1484588368000,
//                      1
//                      ],
//                      [
//                      1484588667000,
//                      1
//                      ]
//                      ],
//                      price_usd: [
//                      [
//                      1484588368000,
//                      829.936
//                      ],
//                      [
//                      1484588667000,
//                      829.762
//                      ]
//                      ],
//                      volume_usd: [
//                      [
//                      1484588368000,
//                      76480000
//                      ],
//                      [
//                      1484588667000,
//                      76481100
//                      ]
//                      ]
//                      }"""
//      val json: JsValue = Json.parse(wsResponse.body)
//      json("market_cap_by_available_supply")
//      json("price_btc")
//      json("price_usd")
//      json("volume_usd")
//
//    }


//    val url = s"https://graphs2.coinmarketcap.com/currencies/$currency/${from.toInstant.getEpochSecond}/${to.toInstant.getEpochSecond}/"
//    wsClient.url("http://www.google.com").get().map(parseData)
//  }
}
