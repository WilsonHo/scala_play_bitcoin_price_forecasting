package bao.ho.service.crawler

import java.util.Date

import bao.ho.configuration.Global
import bao.ho.models.CurrencyInfo

import scala.concurrent.Future

trait CurrencyCrawlerService extends Global {
  def crawlCurrencyData(currency: String, from: Date, to: Date): Future[Seq[CurrencyInfo]]
}
