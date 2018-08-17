import configuration.{Global, Injector}
import converting.DateUtils
import services.crawling.CurrencyCrawlerService

import scala.util.{Failure, Success}


object Main extends Global with Injector {

  def main(args: Array[String]): Unit = {
    //    val myConfiguration: MyConfiguration = injector.getInstance(classOf[MyConfiguration])

    //    println(myConfiguration.config.get[String]("demo"))
    val crawlerService = injector.getInstance(classOf[CurrencyCrawlerService])

    val from = DateUtils.convertStringToDate("2018-01-01 00:00:00+0000")
    val to = DateUtils.convertStringToDate("2018-01-01 00:30:00+0000")
    import scala.concurrent.ExecutionContext.Implicits._


  }
}

