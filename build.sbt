name := "furet"

version := "0.1"

scalaVersion := "2.9.0-1"

pollInterval := 300

libraryDependencies ++= Seq(
    "com.mongodb.casbah" %% "casbah" % "2.1.5.0",
    "org.clapper" % "grizzled-slf4j_2.9.0" % "0.5",
    "org.slf4j" % "slf4j-simple" % "1.6.1" % "runtime"
)

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"

// define the statements initially evaluated when entering 'console', 'console-quick', or 'console-project'
initialCommands := """
import System.{currentTimeMillis => now}
def time[T](f: => T): T = {
    val start = now
        try { f } finally { println("Elapsed: " + (now - start)/1000.0 + " s") }
}
"""
