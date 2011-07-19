object Console {
  def main(args: Array[String]) = {
    println(version)
    createCommand(args(0)).run
    println("bye")
  }
  def createCommand(name: String): Command = name match {
    case "update" => new TestCommand
    case "list" => new ListCommand
    case "help" => new HelpCommand
    case _ => new NotFoundCommand
  }
  def version = "furet 0.1.0"
}
