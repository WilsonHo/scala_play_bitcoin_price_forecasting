lazy val commonSettings = Seq(
  organization := "bao.ho",
  scalaVersion := "2.12.6",
  scalacOptions := Seq("-deprecation", "-unchecked", "-encoding", "utf8", "-Xlint"),
  fork := true
)

lazy val util = (project in file("util"))
  .settings(
    commonSettings,
    name := "util",
    version := "0.1",
    libraryDependencies ++= Seq(
      dependencies.scalatest,
      dependencies.scalactic,
      dependencies.scalaLogging,
      dependencies.scalaLoggingSlf4j,
      dependencies.scalaLoggingApi
    )
  )

lazy val model = (project in file("model"))
  .settings(
    commonSettings,
    name := "model",
    version := "0.1",
    libraryDependencies ++= Seq(
      dependencies.playJson,
      dependencies.slick,
      dependencies.guice
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
      ws,
      cacheApi,
      guice,
      dependencies.scalaGuice,
      dependencies.postgres,
      dependencies.playJson,
      dependencies.slickHikaricp,
      dependencies.akkaActor,
      dependencies.akkaStream,
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
      "com.typesafe.play" %% "play-cache" % "2.6.17",
      "com.github.karelcemus" %% "play-redis" % "2.1.1",
      "io.swagger" %% "swagger-play2" % "1.6.0"
    )
  )
  .aggregate(model, util)
  .dependsOn(model, util)
  .enablePlugins(PlayScala)


lazy val spark = (project in file("spark"))
  .settings(
    commonSettings,
    name := "spark",
    version := "0.1",
    libraryDependencies ++= Seq(
      dependencies.postgres,
      dependencies.playJson,
      dependencies.scalatest,
      dependencies.sparkCore,
      dependencies.sparkStreaming,
      dependencies.sparkSql,
      dependencies.sparkHive,
      dependencies.sparkRepl,
      dependencies.sparkMllib
    )
  )
  .aggregate(model, util)
  .dependsOn(model, util)

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
      dependencies.playJson,
      dependencies.scalatest,
      dependencies.akkaActor,
      dependencies.akkaStream,
      dependencies.slick,
      dependencies.slickHikaricp
    )
  )
  .aggregate(model, util)
  .dependsOn(model, util)


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
    val playSlickV = "3.0.3"
    val scalatestV = "3.0.4"
    val akkaV = "2.5.14"
    val scalaLoggingV = "3.9.0"
    val scalaLoggingSlf4jV = "2.1.2"
    val scalaLoggingApiV = "2.1.2"
    val slickV = "3.2.3"
    val scalaGuiceV = "4.2.1"
    val sparkV = "2.3.1"
  }

lazy val dependencies =
  new {
    val scalatest = "org.scalatest" %% "scalatest" % dependencyVersion.scalatestV % "test"
    val scalactic = "org.scalactic" %% "scalactic" % dependencyVersion.scalatestV
    val postgres = "org.postgresql" % "postgresql" % dependencyVersion.postgresV
    val playAhcWsStandalone = "com.typesafe.play" %% "play-ahc-ws-standalone" % dependencyVersion.playWsStandaloneV
    val playWsStandalone = "com.typesafe.play" %% "play-ws-standalone" % dependencyVersion.playWsStandaloneV
    val playWsStandaloneJson = "com.typesafe.play" %% "play-ws-standalone-json" % dependencyVersion.playWsStandaloneV
    val playWsStandaloneXml = "com.typesafe.play" %% "play-ws-standalone-xml" % dependencyVersion.playWsStandaloneV
    val playJson = "com.typesafe.play" %% "play-json" % dependencyVersion.playJsonV
    val guice = "com.google.inject" % "guice" % dependencyVersion.guiceV
    val playSlick = "com.typesafe.play" %% "play-slick" % dependencyVersion.playSlickV
    val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % dependencyVersion.playSlickV
    val akkaActor = "com.typesafe.akka" %% "akka-actor" % dependencyVersion.akkaV
    val akkaStream = "com.typesafe.akka" %% "akka-stream" % dependencyVersion.akkaV
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % dependencyVersion.scalaLoggingV
    val scalaLoggingSlf4j = "com.typesafe.scala-logging" % "scala-logging-slf4j_2.11" % dependencyVersion.scalaLoggingSlf4jV
    val scalaLoggingApi = "com.typesafe.scala-logging" % "scala-logging-api_2.11" % dependencyVersion.scalaLoggingApiV
    val slick = "com.typesafe.slick" %% "slick" % dependencyVersion.slickV
    val slickHikaricp = "com.typesafe.slick" %% "slick-hikaricp" % dependencyVersion.slickV
    val scalaGuice = "net.codingwell" %% "scala-guice" % dependencyVersion.scalaGuiceV
    val sparkCore = "org.apache.spark" % "spark-core_2.11" % dependencyVersion.sparkV
    val sparkStreaming = "org.apache.spark" % "spark-streaming_2.11" % dependencyVersion.sparkV
    val sparkSql = "org.apache.spark" % "spark-sql_2.11" % dependencyVersion.sparkV
    val sparkHive = "org.apache.spark" % "spark-hive_2.11" % dependencyVersion.sparkV
    val sparkRepl = "org.apache.spark" % "spark-repl_2.11" % dependencyVersion.sparkV
    val sparkMllib = "org.apache.spark" % "spark-mllib_2.11" % "2.3.1" % dependencyVersion.sparkV
  }
