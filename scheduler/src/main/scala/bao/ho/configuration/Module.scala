package bao.ho.configuration

import bao.ho.service.CurrencyCrawlerService
import bao.ho.service.crawler.{CoinMarketCapCrawler, CurrencyCrawlerService}
import bao.ho.service.woker.{Worker, WorkerService}
import com.google.inject.AbstractModule
import com.typesafe.config.ConfigFactory
import play.api.Configuration
//import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.ws.WSClient
import play.api.libs.ws.ahc.AhcWSClient
//import services.database.MyConfiguration

import scala.concurrent.ExecutionContext


class Module extends AbstractModule with Global {

  val CONF_FILE_PATH = "application.conf"

  override def configure() = {
    val config: Configuration = Configuration(ConfigFactory.load(CONF_FILE_PATH))
//    bind(classOf[MyConfiguration]).toInstance(new MyConfiguration(config))
    bind(classOf[CurrencyCrawlerService]).to(classOf[CoinMarketCapCrawler])
    bind(classOf[WSClient]).toInstance(AhcWSClient())
    bind(classOf[WorkerService]).to(classOf[Worker])
    bind(classOf[ExecutionContext])
      .toInstance(scala.concurrent.ExecutionContext.Implicits.global)
//    bind(classOf[DatabaseConfigProvider])
  }

}
