name := "akka-essentials-rmt"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.15"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.3.15"
libraryDependencies += "com.typesafe.akka" %% "akka-kernel" % "2.3.15"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.3.15"
// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.11
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4"
