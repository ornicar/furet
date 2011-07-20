package furet

import furet.dao._
import furet.model._

trait Command {
  def run
}

class ListCommand extends Command {
  def run = {
    new AlbumRepo().findAll foreach print
  }
}

class HelpCommand extends Command {
  def run = {
    println("furet update       Update the database, synchronizing it with the filesystem")
    println("furet help         Display this help")
  }
}

class TestCommand extends Command {
  def run = {
    println("Update the database")
  }
}

class NotFoundCommand extends HelpCommand {
  override def run = {
    println("This is not a valid furet command")
    super.run
  }
}
