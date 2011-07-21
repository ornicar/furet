package furet

object Console {
  def main(args: Array[String]) = {
    println(version)
    var command = "sync"
    if (!args.isEmpty) command = args.head
    createCommand(command).run
    println("bye")
  }
  def createCommand(name: String): Command = name match {
    case "sync" => new SyncCommand
    case "list" => new ListCommand
    case "help" => new HelpCommand
  }
  def version = "furet 0.1.0"
}
