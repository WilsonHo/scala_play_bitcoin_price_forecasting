package controllers

import io.swagger.annotations.{Api, ApiParam, ApiResponse, ApiResponses}
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext}
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

    currencyInfoService
      .getPrice(currency, from, to)
      .map(xx => Ok(Json.toJson(xx)))
  }
}
