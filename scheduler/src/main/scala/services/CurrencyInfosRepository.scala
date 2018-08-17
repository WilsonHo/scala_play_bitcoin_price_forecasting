package services

import java.util.Date

import dao.{CurrencyDAO, CurrencyInfoDAO}
import javax.inject.Inject
import models.CurrencyInfo

import scala.concurrent.{ExecutionContext, Future}

class CurrencyInfosRepository @Inject()(crawlingService: CrawlingCurrencyDataService, currencyDao: CurrencyDAO, currencyInfoDAO: CurrencyInfoDAO)(implicit executionContext: ExecutionContext) {
//  def importHistoricData(currency: String, from: Date, to: Date) = {
//    val data = for {
//      currInfos <- crawlingService.crawlCurrencyData(currency, from, to)
//      currInfo <- currInfos
//      currIdOpt <- currencyDao.findByCurrency(currency)
//      currId <- currIdOpt
//    } yield currencyInfoDAO.insert(CurrencyInfo(currInfo.timestamp, currId.id, currInfo.marketCapByAvailableSupply, currInfo.priceUsd, currInfo.volume))
//  }
}
