package furet

object Console {
  def main(args: Array[String]) = {
    println(version)
    createCommand(args.headOption).run
    println("bye")
  }
  def createCommand(name: Option[String]): Command = name match {
    case Some("sync") => new SyncCommand
    case Some("drop") => new DropCommand
    case Some("list") => new ListCommand
    case Some("help") => new HelpCommand
    case _ => new NotFoundCommand
  }
  def version = "furet 0.1.0"
}
