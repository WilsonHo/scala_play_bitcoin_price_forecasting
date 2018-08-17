package dao

import javax.inject.Inject
import models.Currency
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class CurrencyDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val currencies = TableQuery[CurrenciesTable]

  def find(id: Int) =
    db.run((for (currency <- currencies if currency.id === id) yield currency).result.headOption)

  def findByCurrency(currency: String) =
    db.run((for (cur <- currencies if cur.currency === currency) yield cur).result.headOption)


  def all(): Future[Seq[Currency]] = db.run(currencies.result)

  def insert(currency: Currency): Future[Unit] = db.run(currencies += currency).map { _ => () }

  private class CurrenciesTable(tag: Tag) extends Table[Currency](tag, "currencies") {

    def id = column[Int]("id", O.PrimaryKey)

    def currency = column[String]("currency")

    def abbreviation = column[String]("abbreviation")

    def currencyType = column[String]("currency_type")

    def * = (id, currency, abbreviation, currencyType) <> ((Currency.apply _).tupled, Currency.unapply)
  }

}