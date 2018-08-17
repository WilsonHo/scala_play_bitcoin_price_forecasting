package services


import java.util.Date

import akka.actor.Status.Status
import dao.{CurrencyDAO, CurrencyInfoDAO}
import javax.inject.Inject
import services.crawling.CurrencyCrawlerService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class Worker @Inject()(crawler: CurrencyCrawlerService, currencyDao: CurrencyDAO, currencyInfoDAO: CurrencyInfoDAO)(implicit executionContext: ExecutionContext) {
  def importHistoricData(currency: String, from: Date, to: Date): Future[Status] = {
    val data = crawler.crawlCurrencyData("bitcoin", from, to)
    data onComplete {
      case Success(lines) => lines.foreach(println)
      case Failure(f) => println(f)
    }

    //    val data = for {
    //      currInfos <- crawlingService.crawlCurrencyData(currency, from, to)
    //      currInfo <- currInfos
    //      currIdOpt <- currencyDao.findByCurrency(currency)
    //      currId <- currIdOpt
    //    } yield currencyInfoDAO.insert(CurrencyInfo(currInfo.timestamp, currId.id, currInfo.marketCapByAvailableSupply, currInfo.priceUsd, currInfo.volume))
  }
}
