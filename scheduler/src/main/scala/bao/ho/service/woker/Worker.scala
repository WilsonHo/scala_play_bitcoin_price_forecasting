package bao.ho.service.woker

import java.util.Date

import bao.ho.models.Currency
import bao.ho.service.crawler.CurrencyCrawlerService
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}


class Worker @Inject()(crawler: CurrencyCrawlerService)(implicit executionContext: ExecutionContext) //, currencyDao: CurrencyDAO, currencyInfoDAO: CurrencyInfoDAO)
  extends WorkerService {
  def importHistoricData(currency: String, from: Date, to: Date): Future[String] = {
    for {
      currInfos <- crawler.crawlCurrencyData(currency, from, to)
      currInfo <- currInfos
      currOpt <- Future {
        Some(Currency(1, "bitcoin", "btc", "crypto"))
      }
    } println(currInfo.copy(currInfo.timestamp, currOpt.get.id, currInfo.marketCapByAvailableSupply, currInfo.priceUsd, currInfo.volume))
    //    val currencyInfos: Future[Seq[CurrencyInfo]] = for {
    //      currInfos <- crawler.crawlCurrencyData(currency, from, to)
    //      currInfo <- currInfos
    //      currOpt <- Future {
    //        Some(Currency(1, "bitcoin", "btc", "crypto"))
    //      }
    //      curr <- currOpt
    //    } yield )
    //    currencyInfos onComplete {
    //      case Success(lines) => lines.foreach(println)
    //      case Failure(f) => println(f)
    //    }

    Future("")

  }

  //      data

  //    val data = for {
  //      currInfos <- crawlingService.crawlCurrencyData(currency, from, to)
  //      currInfo <- currInfos
  //          currencyDao.findByCurrency(currency)
  //      currId <- currIdOpt
  //    } yield currencyInfoDAO.insert(CurrencyInfo())
}
