package configuration

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.Guice

trait Global {

  import scala.concurrent.ExecutionContext.Implicits._
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
}
