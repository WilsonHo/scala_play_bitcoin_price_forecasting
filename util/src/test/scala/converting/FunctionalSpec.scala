package converting

/**
  * Functional tests start a Play application internally, available
  * as `app`.
  */
class FunctionalSpec extends PlaySpec with GuiceOneAppPerSuite {


  def instanceBindingExample {
    // if you want to bind to a specific instance of a class...
    class InstanceExampleModule extends AbstractModule {
      @Override
      override protected def configure() {
        bind(classOf[String]).toInstance("foo")
      }

      @Provides
      def guiceSpecProvider: GuiceSpec.type = GuiceSpec
    }

    object GuiceSpec {
      @Inject
      val s: String = null

      def get() = s
    }

    val injector = Guice.createInjector(new InstanceExampleModule)
    println(s"GuiceSpec.s = ${GuiceSpec.s}")
    //    val service = injector.getInstance(classOf[AService])
    //    assertEquals("instance1", service.service)
  }


  //  "Guice" must {
  //    "inject into Scala objects" in {
  //      val injector = Guice.createInjector(new ScalaModule() {
  //        def configure() {
  //          bind[String].toInstance("foo")
  //          bind[GuiceSpec.type].toInstance(GuiceSpec)
  //        }
  //      })
  //      injector.getInstance(classOf[String]) must equal("foo")
  //      GuiceSpec.get must equal("foo")
  //    }
  //  }


  //
  //  "Routes" should {
  //
  //    "send 404 on a bad request" in  {
  //      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
  //    }
  //
  //    "send 200 on a good request" in  {
  //      route(app, FakeRequest(GET, "/")).map(status(_)) mustBe Some(OK)
  //    }
  //
  //  }
  //
  //  "HomeController" should {
  //
  //    "render the index page" in {
  //      val home = route(app, FakeRequest(GET, "/")).get
  //
  //      status(home) mustBe Status.OK
  //      contentType(home) mustBe Some("text/html")
  //      contentAsString(home) must include ("Your new application is ready.")
  //    }
  //
  //  }
  //
  //  "CountController" should {
  //
  //    "return an increasing count" in {
  //      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "0"
  //      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "1"
  //      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "2"
  //    }
  //
  //  }
}
