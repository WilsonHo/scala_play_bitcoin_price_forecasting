package services.crawling

import java.util.Date

import configuration.Global
import models.CurrencyInfo

import scala.concurrent.Future

trait CurrencyCrawlerService extends Global {
  def crawlCurrencyData(currency: String, from: Date, to: Date): Future[Seq[CurrencyInfo]]
}
