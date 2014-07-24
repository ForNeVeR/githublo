name := "githublo"

version := "0.1"

mainClass in (Compile, run) := Some("me.fornever.githublo.Application")

scalaVersion := "2.11.0"

resolvers += "trello4j" at "https://raw.github.com/joelso/joelso-mvn-repo/master/snapshots/"

libraryDependencies ++= Seq(
  "org.eclipse.mylyn.github" % "org.eclipse.egit.github.core" % "2.1.5",
  "org.trello4j" % "trello4j" % "1.0-SNAPSHOT"
)
