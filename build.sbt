name := "furet"

version := "0.1"

scalaVersion := "2.9.0-1"

pollInterval := 300

libraryDependencies ++= Seq(
    "com.mongodb.casbah" %% "casbah" % "2.1.5.0",
    "org.slf4j" % "slf4j-simple" % "1.6.1" % "runtime",
    "com.novus" %% "salat-core" % "0.0.8-SNAPSHOT"
)

resolvers ++= Seq(
    "repo.novus rels" at "http://repo.novus.com/releases/",
    "repo.novus snaps" at "http://repo.novus.com/snapshots/"
)

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"
