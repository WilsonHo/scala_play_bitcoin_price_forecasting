package controllers

import dao.CurrencyDAO
import io.swagger.annotations.{Api, ApiParam, ApiResponse, ApiResponses}
import javax.inject.Inject
import models.Currency
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
//import scala.concurrent.duration._

import play.api.cache.redis.CacheApi

@Api
class ApplicationController @Inject()(cache: CacheApi,
                                      catDao: CurrencyDAO,
                                      controllerComponents: ControllerComponents
                                     )(implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Pet not found")))
  def index(@ApiParam(value = "ID of the pet to fetch") demo: String) = Action.async {
    val abc = cache.get[String]("key")
    if (abc.isEmpty) {
      catDao.all().map {
        cats => {
          if (cats.isEmpty) {
            Ok("Demo")
          }
          else {
            cache.set("key", "xx")
            Ok(Json.toJson(cats))
          }
        }
      }
    } else {
      Future {
        Ok("result of blocking call")
      }
    }
  }


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
