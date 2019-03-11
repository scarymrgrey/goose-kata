name := "goose-kata"

version := "1.0"

scalaVersion := "2.12.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
concurrentRestrictions in Global += Tags.limit(Tags.Test, 1)

