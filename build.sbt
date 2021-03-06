name := "io-monad-exercise"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % "0.18.21",
  "org.http4s" %% "http4s-server" % "0.18.21",
  "org.http4s" %% "http4s-blaze-server" % "0.18.21",
  "org.http4s" %% "http4s-blaze-client" % "0.18.21",
  "org.http4s" %% "http4s-circe" % "0.18.21",
  "io.circe" %% "circe-generic" % "0.10.0-M1",
  "io.circe" %% "circe-literal" % "0.10.0-M1"
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
