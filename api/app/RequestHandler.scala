import javax.inject.Inject

import play.api.http._
import play.api.mvc._
import play.api.routing.Router

class RequestHandler @Inject()(router: Router,
                               errorHandler: HttpErrorHandler,
                               configuration: HttpConfiguration,
                               filters: HttpFilters)
    extends DefaultHttpRequestHandler(router,
                                      errorHandler,
                                      configuration,
                                      filters) {

  override def handlerForRequest(request: RequestHeader): (RequestHeader, Handler) = {
    super.handlerForRequest {
      if (isREST(request)) {
        addTrailingSlash(request)
      } else {
        request
      }
    }
  }

  private def isREST(request: RequestHeader) = {
    request.uri match {
      case uri: String if uri.contains("post") => true
      case _ => false
    }
  }

  private def addTrailingSlash(origReq: RequestHeader): RequestHeader = {
    if (!origReq.path.endsWith("/")) {
      val path = origReq.path + "/"
      if (origReq.rawQueryString.isEmpty) {
        origReq.with(path = path, uri = path)
      } else {
        origReq.copy(path = path, uri = path + s"?${origReq.rawQueryString}")
      }
    } else {
      origReq
    }
  }
}
