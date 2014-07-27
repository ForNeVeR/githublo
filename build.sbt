name := "githublo"

version := "0.1"

mainClass in (Compile, run) := Some("me.fornever.githublo.Application")

scalaVersion := "2.11.0"

resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies ++= Seq(
  "org.eclipse.mylyn.github" % "org.eclipse.egit.github.core" % "2.1.5",
  "org.trello4j" % "trello4j" % "1.0-SNAPSHOT"
)
