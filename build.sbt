name := "githublo"

version := "0.1"

mainClass in (Compile, run) := Some("me.fornever.githublo.Application")

scalaVersion := "2.11.0"

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
  "io.spray" %%  "spray-json" % "1.2.6"
)
