import com.google.inject.{AbstractModule}
import bao.ho.connection.{DbProvider}
import connection.PostgresDBProvider
import dao.{CurrencyDAO, CurrencyDAOImpl, CurrencyInfoDAO, CurrencyInfoDAOImpl}
import services.{CurrencyInfoService, CurrencyInfoServiceImpl}

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
    bind(classOf[CurrencyInfoDAO]).to(classOf[CurrencyInfoDAOImpl])
    bind(classOf[CurrencyInfoService]).to(classOf[CurrencyInfoServiceImpl])
  }
}


