import bao.ho.models.Currency

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import dao.CurrencyDAO
import play.api.Application

/** test the kitty cat database */
class CatDAOSpec {
//  extends Specification {
//
//  "CatDAO" should {
//    "work as expected" in new WithApplicationLoader {
//      val app2dao = Application.instanceCache[CurrencyDAO]
//      val dao: CurrencyDAO = app2dao(app)
//
//      val testKitties = Set(
//        Currency("kit", "black"),
//        Currency("garfield", "orange"),
//        Currency("creme puff", "grey")
//      )
//
//      Await.result(Future.sequence(testKitties.map(dao.insert)), 1 seconds)
//      val storedCats = Await.result(dao.all(), 1 seconds)
//
//      storedCats.toSet must equalTo(testKitties)
//    }
//  }
}
