package dao

import bao.ho.models.Currency

import scala.concurrent.Future

trait CurrencyDAO {
  def find(id: Int): Future[Option[Currency]]

  def findByCurrency(currency: String): Future[Option[Currency]]

  def all(): Future[Seq[Currency]]

  def insert(currency: Currency): Future[Int]
}

