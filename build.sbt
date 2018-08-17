//TODO: Refactor this file
lazy val commonSettings = Seq(
  organization := "bao.ho",
  scalaVersion := "2.12.6"
)

lazy val util = (project in file("util"))
  .settings(
    commonSettings,
    name := "util",
    version := "0.1"
  )

lazy val model = (project in file("model"))
  .settings(
    commonSettings,
    name := "model",
    version := "0.1",
    libraryDependencies ++= Seq(
      dependencies.playJson,
      dependencies.guice,
      dependencies.playSlick,
      dependencies.playSlickEvolutions
    )
  )

lazy val api = (project in file("api"))
  .settings(
    commonSettings,
    name := "api",
    version := "0.1",
    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
    libraryDependencies ++= Seq(
      guice,
      ws,
      cacheApi,
      dependencies.postgres,
      dependencies.playJson,
      dependencies.playSlick,
      dependencies.playSlickEvolutions,
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
      "com.typesafe.play" %% "play-cache" % "2.6.17",
      "com.github.karelcemus" %% "play-redis" % "2.1.1",
      "io.swagger" %% "swagger-play2" % "1.6.1-SNAPSHOT"
    )
  )
  .aggregate(model)
  .dependsOn(model)
  .enablePlugins(PlayScala)

lazy val spark = (project in file("spark"))
  .settings(
    commonSettings,
    name := "spark",
    version := "0.1",
    libraryDependencies ++= Seq(
      dependencies.postgres,
      dependencies.playJson
    )
  )
  .aggregate(model)
  .dependsOn(model)


lazy val scheduler = (project in file("scheduler"))
  .settings(
    commonSettings,
    name := "scheduler",
    version := "0.1",
    libraryDependencies ++= Seq(
      ws,
      dependencies.guice,
      dependencies.playAhcWsStandalone,
      dependencies.playWsStandalone,
      dependencies.playWsStandaloneJson,
      dependencies.playWsStandaloneXml,
      dependencies.postgres,
      dependencies.playJson

    )
  )
  .aggregate(model)
  .dependsOn(model)


lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "scala_play_bitcoin_price_forecasting",
    version := "0.1"
  )
  .aggregate(api, spark, util, scheduler, model)
  .dependsOn(api, spark, util, scheduler, model)

lazy val dependencyVersion =
  new {
    val playWsStandaloneV = "2.0.0-M2"
    val postgresV = "42.0.0"
    val playJsonV = "2.6.9"
    val guiceV = "4.2.0"
    val playSlickV = "3.0.1"

  }

lazy val dependencies =
  new {
    val postgres = "org.postgresql" % "postgresql" % dependencyVersion.postgresV
    val playAhcWsStandalone = "com.typesafe.play" %% "play-ahc-ws-standalone" % dependencyVersion.playWsStandaloneV
    val playWsStandalone = "com.typesafe.play" %% "play-ws-standalone" % dependencyVersion.playWsStandaloneV
    val playWsStandaloneJson = "com.typesafe.play" %% "play-ws-standalone-json" % dependencyVersion.playWsStandaloneV
    val playWsStandaloneXml = "com.typesafe.play" %% "play-ws-standalone-xml" % dependencyVersion.playWsStandaloneV
    val playJson = "com.typesafe.play" %% "play-json" % dependencyVersion.playJsonV
    val guice = "com.google.inject" % "guice" % dependencyVersion.guiceV
    val playSlick = "com.typesafe.play" %% "play-slick" % dependencyVersion.playSlickV
    val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % dependencyVersion.playSlickV


    //    val typesafeConfigV = "1.3.1"
    //    val pureconfigV     = "0.8.0"
    //    val monocleV        = "1.4.0"
    //    val akkaV           = "2.5.6"
    //    val scalatestV      = "3.0.4"
    //    val scalacheckV     = "1.13.5"
    //
    //    val typesafeConfig = "com.typesafe"               % "config"                   % typesafeConfigV
    //    val akka           = "com.typesafe.akka"          %% "akka-stream"             % akkaV
    //    val monocleCore    = "com.github.julien-truffaut" %% "monocle-core"            % monocleV
    //    val monocleMacro   = "com.github.julien-truffaut" %% "monocle-macro"           % monocleV
    //    val pureconfig     = "com.github.pureconfig"      %% "pureconfig"              % pureconfigV
    //    val scalatest      = "org.scalatest"              %% "scalatest"               % scalatestV
    //    val scalacheck     = "org.scalacheck"             %% "scalacheck"              % scalacheckV
  }
