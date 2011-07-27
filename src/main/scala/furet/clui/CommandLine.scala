package furet.clui

import java.io.File

// A command line from the user. This is the front end of the
// command-line interface to furet
object CommandLine {

  val settings = new Settings()
  import settings._

  def errorExit(message: String): Nothing = {
    println("error: " + message)
    sys.exit(2)
  }

  def usageExit(): Nothing = {
    commands.Help.run(List(), settings)
    sys.exit(2)
  }

  def processCommandLine(args: Array[String]) {
    // parse global options
    var argsleft = settings.parseOptions(args.toList)

    // extract the command name and command arguments
    val cmdName :: cmdArgs = argsleft match {
      case Nil => usageExit()
      case a::b => argsleft
    }

    // now find and run the requested command
    Command.named(cmdName) match {
      case None => usageExit()
      case Some(command) =>
        try command.run(cmdArgs, settings)
        catch {
          case er: Exception =>
            if (verbose) throw er
            else errorExit(er.toString)
        }
    }
  }

  def main(args: Array[String]): Unit = {
    processCommandLine(args)
  }
}
