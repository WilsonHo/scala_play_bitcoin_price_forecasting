import com.google.inject.{AbstractModule, Provides}
import bao.ho.connection.DbProvider
import bao.ho.tables.Currencies
import connection.PostgresDBProvider
import dao.{CurrencyDAO, CurrencyDAOImpl}

/**
  * This class is a Guice module that tells Guice how to bind several
  * different types. This Guice module is created when the Play
  * application starts.
  *
  * Play will automatically use any class called `Module` that is in
  * the root package. You can create modules in other locations by
  * adding `play.modules.enabled` settings to the `application.conf`
  * bao.ho.configuration file.
  */
class Module extends AbstractModule {

  override def configure() = {
    bind(classOf[DbProvider]).to(classOf[PostgresDBProvider])
    bind(classOf[CurrencyDAO]).to(classOf[CurrencyDAOImpl])
//    bind(classOf[DbProvider]).toInstance("foo")
    // Use the system clock as the default implementation of Clock
    //    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    //    // Ask Guice to create an instance of ApplicationTimer when the
    //    // application starts.
    //    bind(classOf[ApplicationTimer]).asEagerSingleton()
    //    // Set AtomicCounter as the implementation for Counter.
    //    bind(classOf[Counter]).to(classOf[AtomicCounter])
  }
}


