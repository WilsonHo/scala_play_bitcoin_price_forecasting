package dao


import javax.inject.Inject
import models.CurrencyInfo
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class CurrencyInfoDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val currencyInfos = TableQuery[CurrencyInfosTable]

  def all(): Future[Seq[CurrencyInfo]] = db.run(currencyInfos.result)

  def insert(currencyInfo: CurrencyInfo): Future[Unit] = db.run(currencyInfos += currencyInfo).map { _ => () }

  private class CurrencyInfosTable(tag: Tag) extends Table[CurrencyInfo](tag, "currency_infos") {

    def timestamp = column[Long]("timestamp", O.PrimaryKey)

    def currencyId = column[Int]("currency_id")

    def marketCapByAvailableSupply = column[Long]("market_cap_by_available_supply")

    def priceUsd = column[Double]("price_usd")

    def volume = column[Long]("volume")

    def * = (timestamp, currencyId, marketCapByAvailableSupply, priceUsd, volume) <> ((CurrencyInfo.apply _).tupled, CurrencyInfo.unapply)
  }

}