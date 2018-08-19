package controllers

import javax.inject.Inject
import jep.Jep
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import play.api.cache.redis.CacheApi
import services.CurrencyInfoService

//@Api
class ApplicationController @Inject()(cache: CacheApi,
                                      currencyInfoService: CurrencyInfoService,
                                      cc: ControllerComponents
                                     )(implicit executionContext: ExecutionContext) extends AbstractController(cc) {


  //  @ApiResponses(Array(
  //    new ApiResponse(code = 400, message = "Invalid Crypto currency name"),
  //    new ApiResponse(code = 404, message = "Invalid timestamp")))
  //@ApiParam(value = "Crypto currency name")
  //@ApiParam(value = "From timestamp")
  //@ApiParam(value = "To timestamp")
  def getPrice(currency: String,
               from: Long,
               to: Long): Action[AnyContent] = Action.async {

    currencyInfoService
      .getPrice(currency, from, to)
      .map(xx => Ok(Json.toJson(xx)))
  }

  def index() = Action.async {
    println("Hello1")

    val javaLibPath = System.getProperty("java.library.path")
    val envVars = System.getenv
    System.out.println(envVars.get("Path"))
    System.out.println(javaLibPath)
    import scala.collection.JavaConversions._
    for (in <- envVars.keySet) {
      System.err.println("examining " + in)
      if (envVars.get(in) == javaLibPath) System.out.println(in)
    }
    val jep = new Jep()
    println("Hello2")
    jep.runScript("add.py")
    val a = 2
    val b = 3
    println("Hello")
    // There are multiple ways to evaluate. Let us demonstrate them:
    jep.eval(s"c = add($a, $b)")
    val ans = jep.getValue("c").asInstanceOf[Int]

    //    val ans2 = jep.invoke("add", a, b).asInstanceOf[Int]
    //    println(ans2)

    Future {
      Ok(s"Welcome to Crypto currency world ")
    }
  }

  //  @ApiResponses(Array(
  //    new ApiResponse(code = 400, message = "Invalid Crypto currency name"),
  //    new ApiResponse(code = 404, message = "Invalid timestamp")))
  //@ApiParam(value = "Crypto currency name")
  def predictPrice(currency: String) = Action.async {
    Future {
      Ok("")
    }
    //    futureSum.map { s => Ok(s"A non-blocking call to Spark with result: ${s + 1000}") }
  }


}
