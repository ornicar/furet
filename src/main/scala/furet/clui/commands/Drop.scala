package furet.clui.commands

import furet.clui._

object Drop extends Command {
  val name = "drop"
  val oneLineHelp = "drop the database"
  val fullHelp =
    ("furet drop\n\n" +
     "Drops the database. All bands and records are removed\n" +
     "Does *not* write the filesystem.\n")

  def run(args: List[String], settings: Settings) {
    println("Drop database")
    if (!settings.dryrun)
      settings.domain.db.drop()
  }
}

