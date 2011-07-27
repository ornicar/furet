package furet.clui.commands

import furet.clui._

object Search extends Command {
  val name = "search"
  val oneLineHelp = "search for records in the database"
  val fullHelp =
    ("furet search term\n\n" +
     "Searches for records based on a given term\n")

  def run(args: List[String], settings: Settings) {
    val term = args match {
      case Nil => usageExit("You must provide a search term")
      case List(term) => term
      case _ => usageExit("Too many arguments")
    }
    val results = settings.domain.search(term).toList
    val sorted = results sortWith (_.tokens < _.tokens)
    sorted foreach println
    println("Found %d records matching the term \"%s\"".format(results.size, term))
  }
}
