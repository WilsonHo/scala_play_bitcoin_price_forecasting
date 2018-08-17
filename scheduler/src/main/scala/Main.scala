//import akka.actor.ActorSystem
//import akka.stream.ActorMaterializer
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws._
import play.api.libs.ws.ahc._
import play.libs.ws.WS

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
//
//import scala.concurrent.Future
//import play.api.libs.ws.ahc.
//class CrawlingDataService extends AhcWSComponents {
//
//}
import play.api.Configuration
import com.typesafe.config.ConfigFactory


object Main {

  import scala.concurrent.ExecutionContext.Implicits._

  def main(args: Array[String]): Unit = {
    val jString =
      """{"market_cap_by_available_supply":[[1484588368000,13367198196],[1484588667000,13364395700]],"price_btc":[[1484588368000,1],[1484588667000,1]],"price_usd":[[1484588368000,829.936],[1484588667000,829.762]],"volume_usd":[[1484588368000,76480000],[1484588667000,76481100]]}"""
    val json: JsValue = Json.parse(jString)
    val all = json("market_cap_by_available_supply").as[Seq[Seq[Double]]] ++
      json("price_btc").as[Seq[Seq[Double]]] //++
     println (json("price_usd").as[Seq[Seq[Double]]]) //++
//      json("volume_usd").as[Seq[Seq[Long]]]

    println(all)
    //    val config = Configuration(ConfigFactory.load("./conf/application.conf"))
    //    print(config)
    //
    //    val foo: String = config.get[String]("demo")
    //    print(foo)
    //    implicit val system = ActorSystem()
    //    implicit val materializer = ActorMaterializer()
    //    val wsClient = AhcWSClient()
    //
    //    call(wsClient)
    //      .andThen { case _ => wsClient.close() }
    //      .andThen { case _ => system.terminate() }
  }


  //  def call(wsClient: WSClient): Future[Unit] = {
  //    wsClient.url("http://www.google.com").get().map { response =>
  //      val statusText: String = response.statusText
  //      println(s"Got a response $statusText")
  //    }
  //  }
}

