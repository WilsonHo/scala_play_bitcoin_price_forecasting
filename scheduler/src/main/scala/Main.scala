import bao.ho.configuration.{Global, Injector}
import bao.ho.service.woker.WorkerService
import converting.DateUtils


object Main extends Global with Injector {

  def main(args: Array[String]): Unit = {
    //    val myConfiguration: MyConfiguration = injector.getInstance(classOf[MyConfiguration])

    //    println(myConfiguration.config.get[String]("demo"))
    val worker = injector.getInstance(classOf[WorkerService])

    val from = DateUtils.convertStringToDate("2018-01-01 00:00:00+0000")
    val to = DateUtils.convertStringToDate("2018-01-01 00:30:00+0000")
    worker.importHistoricData("bitcoin", from, to)

  }
}

