package furet.clui.commands

import furet.clui._

object FindDup extends Command {
  val name = "find-dup"
  val oneLineHelp = "find duplicated records in the filesystem"
  val fullHelp =
    ("furet -d /dir/to/music find-dup\n\n" +
     "Finds duplicated records in the filesystem\n" +
     "Does *not* write the filesystem.\n")

  def run(args: List[String], settings: Settings) {
    val dups = settings.domain.findDup
    for ((record, dirs) <- dups) {
      println("%d occurences of %s:".format(dirs.size, record))
      dirs foreach (dir => println(" - " + dir.getAbsolutePath))
      println()
    }
    println("Found %d records duplicated in the filesystem.".format(dups.size))
  }
}
