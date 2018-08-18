package controllers

import bao.ho.models.CurrencyInfo
import dao.CurrencyDAO
import io.swagger.annotations.{Api, ApiParam, ApiResponse, ApiResponses}
import javax.inject.Inject
import play.api.libs.json.{JsObject, JsValue, Json}
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import play.api.cache.redis.CacheApi
import services.CurrencyInfoService

@Api
class ApplicationController @Inject()(cache: CacheApi,
                                      currencyInfoService: CurrencyInfoService,
                                      cc: ControllerComponents
                                     )(implicit executionContext: ExecutionContext) extends AbstractController(cc) {


  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid Crypto currency name"),
    new ApiResponse(code = 404, message = "Invalid timestamp")))
  def getPrice(@ApiParam(value = "Crypto currency name") currency: String,
               @ApiParam(value = "From timestamp") from: Long,
               @ApiParam(value = "To timestamp") to: Long) = Action.async {

    println(currencyInfoService)
    currencyInfoService
      .getPrice(currency, from, to)
      .map(xx => Ok(Json.toJson(xx)))

    //    .map { list: List[CurrencyInfo] =>
    //      val json = JsObject(Seq(
    //        "x" -> JsString("1"),
    //        "list" -> Json.toJson(list)
    //      ))
    //      Ok(json)
    //    }
    //
    //    val result = Json.toJson()
    //      Ok(result)
  }


  //  def index(@ApiParam(value = "ID of the pet to fetch") demo: String) = Action.async {
  //    val abc = cache.get[String]("key")
  //    if (abc.isEmpty) {
  //      catDao.all().map {
  //        cats => {
  //          if (cats.isEmpty) {
  //            Ok("Demo")
  //          }
  //          else {
  //            cache.set("key", "xx")
  //            Ok(Json.toJson(cats))
  //          }
  //        }
  //      }
  //    } else {
  //      Future {
  //        Ok("result of blocking call")
  //      }
  //    }
  //  }


  //
  //  def demo(aaa: String) = Action.async{
  //
  //  }
  //  def getPetById(id: String) = Action.async {
  //    implicit request =>
  //      JsonResponse(new value.ApiResponse(404, "Pet not found"), 404)
  //      petData.getPetbyId(getLong(0, 100000, 0, id)) match {
  //        case Some(pet) => JsonResponse(pet)
  //        case _ => JsonResponse(new value.ApiResponse(404, "Pet not found"), 404)
  //      }
  //}

  //  val catForm = Form(
  //    mapping(
  //      "name" -> text(),
  //      "color" -> text()
  //    )(Currency.apply)(Currency.unapply)
  //  )


  //  def insertCat = Action.async {
  //    implicit request =>
  //      val cat: Currency = catForm.bindFromRequest.get
  //      catDao.insert(cat).map(_ => Redirect(routes.ApplicationController.index("alo")))
  //  }

  //  def insertDog = Action.async { implicit request =>
  //    val dog: Dog = dogForm.bindFromRequest.get
  //    dogDao.insert(dog).map(_ => Redirect(routes.Application.index))
  //  }

  //  @ApiResponses(Array(
  //    new ApiResponse(code = 400, message = "Invalid name supplied"),
  //    new ApiResponse(code = 404, message = "Name not found")))
  //  def sayHello(@ApiParam(value = "Name to say hello") name: String) = Action {
  //    Ok(s"hello $name")
  //  }
}
