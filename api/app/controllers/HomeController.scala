package controllers

import io.swagger.annotations.{Api, ApiParam, ApiResponse, ApiResponses}
import javax.inject._
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
//@Singleton
//class HomeController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
//  extends AbstractController(cc) {
//
//  /**
//   * Create an Action to render an HTML page with a welcome message.
//   * The configuration in the `routes` file means that this method
//   * will be called when the application receives a `GET` request with
//   * a path of `/`.
//   */
//  def index = Action {
//    Ok("demo")
//  }
//
//}

@Api
class HomeController extends Controller {

  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid name supplied"),
    new ApiResponse(code = 404, message = "Name not found")))
  def sayHello(@ApiParam(value = "Name to say hello") name: String) = Action {
    Ok(s"hello $name")
  }
}