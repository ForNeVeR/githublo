seq(lessSettings: _*)

name := "githublo"

version := "0.1"

mainClass in (Compile, run) := Some("me.fornever.githublo.ui.FXApplication")

scalaVersion := "2.11.5"

resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "com.jsuereth" %% "scala-arm" % "1.4",
  "io.trygvis.trello4j" % "trello4j" % "1.0-2013.10.19",
  "org.eclipse.mylyn.github" % "org.eclipse.egit.github.core" % "2.1.5",
  "org.scala-lang.modules" %% "scala-async" % "0.9.2",
  "org.scalafx" %% "scalafxml-core-sfx8" % "0.2.2"
)
