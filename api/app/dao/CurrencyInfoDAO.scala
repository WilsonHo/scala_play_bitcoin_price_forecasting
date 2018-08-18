package dao

import bao.ho.models.CurrencyInfo

import scala.concurrent.Future


trait CurrencyInfoDAO {
  def all(): Future[Seq[CurrencyInfo]]

  def insert(currencyInfo: CurrencyInfo): Future[Int]
}