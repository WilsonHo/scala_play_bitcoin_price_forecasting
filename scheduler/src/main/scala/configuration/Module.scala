package configuration

import com.google.inject.AbstractModule
import com.typesafe.config.ConfigFactory
import play.api.Configuration
import play.api.libs.ws.WSClient
import play.api.libs.ws.ahc.AhcWSClient
import services.crawling.{CoinMarketCapCrawler, CurrencyCrawlerService}
import services.database.MyConfiguration


class Module extends AbstractModule with Global {

  val CONF_FILE_PATH = "./conf/application.conf"

  override def configure() = {
    val config: Configuration = Configuration(ConfigFactory.load(CONF_FILE_PATH))
    bind(classOf[MyConfiguration]).toInstance(new MyConfiguration(config))
    bind(classOf[CurrencyCrawlerService]).to(classOf[CoinMarketCapCrawler])
    bind(classOf[WSClient]).toInstance(AhcWSClient())
  }

}
