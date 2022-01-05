import Dependencies.Libraries

name := "akka-http-tapir-v2"

version := "0.1"

val scalaLangVersion = "2.13.7"

lazy val settings = Seq(
  scalaVersion := scalaLangVersion,
  scalacOptions ++= Seq(
    "-unchecked",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-Xfatal-warnings",
    "-encoding",
    "utf8",
  ),
)

lazy val rootProject = project
  .in(file("."))
  .settings(
    name := "akka-http-tapir-v2",
    scalaVersion := scalaLangVersion,
  )
  .aggregate(common, api)

lazy val common = project
  .settings(
    name := "common",
    settings,
    libraryDependencies ++=
      Libraries.basicDeps ++
        Libraries.tapirDeps,
  )

lazy val api = project
  .settings(
    name := "api",
    settings,
    libraryDependencies ++=
      Libraries.basicDeps ++
        Libraries.akkaDeps,
  )
  .dependsOn(common)
