name := "ScalaJson"

version := "0.1"

scalaVersion := "2.13.4"

val circeVersion = "0.14.2"

libraryDependencies += "io.circe" %% "circe-core" % circeVersion
libraryDependencies += "io.circe" %% "circe-generic" % circeVersion
libraryDependencies += "io.circe" %% "circe-parser" % circeVersion

libraryDependencies += "io.spray" %% "spray-json" % "1.3.6"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2"
