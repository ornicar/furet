package furet

import furet.dao._
import furet.model._
import furet.sync._

trait Command {
  def run
}

class ListCommand extends Command {
  def run = {
    new AlbumDao().findAll foreach println
  }
}

class SyncCommand extends Command {
  def run = {
    println("Sync DB/FS")
    new Sync().sync
  }
}

class HelpCommand extends Command {
  def run = {
    println("furet update       Update the database, synchronizing it with the filesystem")
    println("furet help         Display this help")
  }
}

class NotFoundCommand extends HelpCommand {
  override def run = {
    println("This is not a valid furet command")
    super.run
  }
}
