ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.tanacasino.example"
ThisBuild / organizationName := "tanacasino.example"
ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-Ypartial-unification",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard",
  "-Xlint",
  "-Xfatal-warnings"
)

lazy val libDependencies = Seq(
  Dependencies.ScalaTest
)

lazy val auto = (project in file("auto"))
  .settings(
    name := "auto",
    libraryDependencies ++= libDependencies
  )

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    libraryDependencies ++= libDependencies
  )

lazy val main = (project in file("main"))
  .settings(
    name := "main",
    libraryDependencies ++= libDependencies
  )
  .dependsOn(core, auto)

lazy val root = (project in file(".")).aggregate(main, core, auto)
