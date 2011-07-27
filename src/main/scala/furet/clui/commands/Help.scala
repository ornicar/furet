package furet.clui.commands

import furet.clui._

object Help extends Command {
  val name = "help"
  val oneLineHelp = "display a help message"
  val fullHelp: String =
    ("furet help [ command ]\n\n" +
     "Display a help message.  If a command is specified, display\n" +
     "help for that option.  Otherwise, display a global help message.\n")

  def run(args: List[String], settings: Settings) {
    args match {
      case Nil =>
        List(
          "furet [ global_options... ] command command_options...",
          settings.fullHelp,
          "Available commands:"
        ) foreach (l => println(l + "\n"))
        for (cmd <- Command.allCommands)
          println(cmd.name + " - " + cmd.oneLineHelp)
      case List(cmdName) => Command.named(cmdName) match {
        case None => println("No cammand named " + cmdName)
        case Some(cmd) => print(cmd.fullHelp)
      }
      case _ => usageExit
    }
  }
}
