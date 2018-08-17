package configuration

import com.google.inject
import com.google.inject.Guice

trait Injector {
  val injector: inject.Injector = Guice.createInjector(new Module)
}
