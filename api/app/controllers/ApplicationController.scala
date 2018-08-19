package controllers

import javax.inject.Inject
import jep.Jep
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import play.api.cache.redis.CacheApi
import services.CurrencyInfoService

class ApplicationController @Inject()(cache: CacheApi,
                                      currencyInfoService: CurrencyInfoService,
                                      cc: ControllerComponents
                                     )(implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  /**
    * Use to get price of the currency base timestamp
    * @param currency
    * @param from
    * @param to
    * @return
    */
  def getPrice(currency: String,
               from: Long,
               to: Long): Action[AnyContent] = Action.async {

    currencyInfoService
      .getPrice(currency, from, to)
      .map(xx => Ok(Json.toJson(xx)))
  }

  def index() = Action.async {
    Future {
      Ok(s"Welcome to Crypto currency world ")
    }
  }


  /**
    * Use this endpoint to get the predict price of currency.
    * @param currency
    * @return
    */
  def predictPrice(currency: String) = TODO

}
