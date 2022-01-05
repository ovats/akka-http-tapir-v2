import sbt._

object Dependencies {

  object Versions {
    val akkaHttpVersion      = "10.2.7"
    val akkaVersion          = "2.6.17"
    val circeVersion         = "0.14.1"
    val akkaHttpCirceVersion = "1.38.2"
    val tapirVersion         = "0.18.3"

    val logbackVersion      = "1.2.7"
    val scalaLoggingVersion = "3.9.4"
  }

  object Libraries {
    // Akka
    val akkaHttp   = "com.typesafe.akka" %% "akka-http"   % Versions.akkaHttpVersion
    val akkaActors = "com.typesafe.akka" %% "akka-actor"  % Versions.akkaVersion
    val akkaStream = "com.typesafe.akka" %% "akka-stream" % Versions.akkaVersion

    // Circe
    val akkaHttpCirce = "de.heikoseeberger" %% "akka-http-circe" % Versions.akkaHttpCirceVersion
    val circeCore     = "io.circe"          %% "circe-core"      % Versions.circeVersion
    val circeGeneric  = "io.circe"          %% "circe-generic"   % Versions.circeVersion

    // Logs
    val logback      = "ch.qos.logback"              % "logback-classic" % Versions.logbackVersion % Runtime
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"   % Versions.scalaLoggingVersion

    // Tapir
    val tapirCore             = "com.softwaremill.sttp.tapir" %% "tapir-core"               % Versions.tapirVersion
    val tapirAkkaHttp         = "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server"   % Versions.tapirVersion
    val tapirJsonCirce        = "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % Versions.tapirVersion
    val tapirOpenApiDocs      = "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % Versions.tapirVersion
    val tapirOpenApiCirceYaml = "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % Versions.tapirVersion
    val tapirSwaggerUI =
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-akka-http" % Versions.tapirVersion exclude ("com.typesafe.akka", "akka-stream_2.12")

    val akkaDeps = Seq(
      akkaActors,
      akkaHttp,
      akkaStream,
      akkaHttpCirce,
    )

    val tapirDeps =
      Seq(tapirCore, tapirAkkaHttp, tapirJsonCirce, tapirOpenApiDocs, tapirOpenApiCirceYaml, tapirSwaggerUI)

    val circeDeps = Seq(
      circeCore,
      circeGeneric,
    )

    val basicDeps = Seq(
      logback,
      scalaLogging,
    )
  }
}
