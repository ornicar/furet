package furet.clui.commands

import furet.clui._
import furet.db._
import furet.sync

object Sync extends Command {
  val name = "sync"
  val oneLineHelp = "synchronize the database with the filesystem"
  val fullHelp =
    ("furet sync\n\n" +
     "Parses the filesystem. Adds, updates, and removes records\n" +
     "in the database to reflect the filesystem state.\n" +
     "Does *not* write the filesystem.\n")

  def run(args: List[String], settings: Settings) {
    import settings._
    println("Synchronize database <- filesystem from:")
    domain.dirs foreach (d => println("- " + d.getAbsolutePath))
    if (!dryrun) domain.sync()
    println(domain.dbStore)
  }
}
