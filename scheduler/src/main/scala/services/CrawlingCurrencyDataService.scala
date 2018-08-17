package services

import java.util.Date

import models.CurrencyInfo

import scala.concurrent.Future

trait CrawlingCurrencyDataService {
  def crawlCurrencyData(currency: String, from: Date, to: Date): Future[Seq[CurrencyInfo]]
}
