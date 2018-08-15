lazy val commonSettings = Seq(
  organization := "bao.ho",
  scalaVersion := "2.12.6"
)


//libraryDependencies ++= Seq(
//  jdbc,
//  anorm,
//  cache,
//  ws
//)
//
//libraryDependencies ++= Seq(
//  "com.typesafe.slick" %% "slick"      % "2.1.0",
//  "org.postgresql"     %  "postgresql" % "9.3-1102-jdbc41"
//)

//lazy val dependencies =
//  new {
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
//  }
//view raw

lazy val sparkDependencies = Seq(
  //  dependencies.typesafeConfig,
  //  dependencies.akka,
  //  dependencies.scalatest  % "test",
  //  dependencies.scalacheck % "test"
)


lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "scala_play_bitcoin_price_forecasting",
    version := "0.1"
  )
//  .aggregate(api, spark, util, scheduler)

lazy val api = (project in file("api"))
  .settings(
    commonSettings,
    name := "api",
    version := "0.1"
    //      libraryDependencies ++ = sparkDependencies
  )
  //  .dependsOn(model)
  .enablePlugins(PlayScala)


lazy val spark = (project in file("spark"))
  .settings(
    commonSettings,
    name := "spark",
    version := "0.1"
  )
//  .dependsOn(model)


lazy val scheduler = (project in file("scheduler"))
  .settings(
    commonSettings,
    name := "scheduler",
    version := "0.1")
//  .dependsOn(model)

lazy val util = (project in file("util"))
  .settings(
    commonSettings,
    name := "util",
    version := "0.1")

lazy val model = (project in file("model"))
  .settings(
    commonSettings,
    name := "model",
    version := "0.1")