package controllers

import javax.inject._
import jep.{Jep, JepConfig, NDArray}
import play.api.Configuration
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class PredictionController @Inject()(config: Configuration,
                                     cc: ControllerComponents
                                    )(implicit executionContext: ExecutionContext) extends AbstractController(cc) {
  /**
    * Use this endpoint to get the predict price of currency.
    *
    * @param currency
    * @return
    */
  def predictPrice(currency: String) = Action.async {
    var jep: Jep = null
    val jep = new Jep(new JepConfig().addSharedModules("numpy"))
    jep.runScript(config
      .getOptional[String]("python")
      .getOrElse("./python/currency_price_prediction.py")
    )

    val fn = "predict_price"
    val f = Array[Float](1.0f, 2.1f, 3.3f, 4.5f, 5.6f, 6.7f)
    val nd = new NDArray[Array[Float]](f)
    val r1 = jep.invoke(fn, nd,currency)
    println(s"config = ${r1}")

//    val ans: String = jep.getValue("r1").asInstanceOf[String]
    jep.close()

    Future {
      Ok(ans)
    }
  }
}
