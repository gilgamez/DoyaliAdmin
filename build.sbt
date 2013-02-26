name := "DoyaliAdmin"

version := "1.0"

scalaVersion := "2.10.0"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "io.spray" % "spray-routing" % "1.1-M7"

libraryDependencies += "io.spray" % "spray-can" % "1.1-M7"

libraryDependencies += "io.spray" %  "spray-json_2.10" % "1.2.3"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.1.0"
