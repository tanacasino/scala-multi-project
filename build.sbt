ThisBuild / scalaVersion := "2.12.8"
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
  "-Xlint",
  "-Xfatal-warnings"
)

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
      Dependencies.JawnParser,
      Dependencies.ScalaTest % Test
    )
  )

lazy val auto = (project in file("auto"))
  .settings(
    name := "auto",
    libraryDependencies ++= Seq(
      Dependencies.Shapeless,
      Dependencies.ScalaTest % Test
    )
  )
  .dependsOn(core)

lazy val main = (project in file("main"))
  .settings(
    name := "main"
  )
  .dependsOn(core, auto)

lazy val root = (project in file(".")).aggregate(main, core, auto)

