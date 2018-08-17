//import akka.actor.ActorSystem
//import akka.stream.ActorMaterializer
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import javax.inject.Inject
import models.CurrencyInfo
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws._
import play.api.libs.ws.ahc._


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
    val speech = """Four score and
                   |seven years ago
                   |our fathers"""
    println(speech)






    //    val all =
    //      ().zipped.toList
    //    println(all)
    //    val next = all
    //          .groupBy(_.head)
    //      .mapValues(_.map(_.last))
    //    //      .map {
    //    //        case (key, value) => CurrencyInfo(key.toLong, NA_ID,)
    //    //      }
    //    println(next)
    //        val aa = for {
    //          a <- List(1, 2)
    //          b <- List(4, 5)
    //        } yield (a, b)
    //    println(aa)
    //
    //    println((List(1, 2), List(4, 5), List(8, 98)).zipped.toList)
    //    //    println((.zip()).zip())
    //    //    println(List(1, 2).zipAll(List(4, 5)))
    //
    //    println(aa)
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

