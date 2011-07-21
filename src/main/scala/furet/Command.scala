package furet

import furet.dao._
import furet.sync._

trait Command {
  def run
}

class ListCommand extends Command {
  def run = {
    new RecordDao().findAll foreach println
  }
}

class SyncCommand extends Command {
  val path = "/home/thib/data/Music" 
  def run = {
    println("Synchronize database with filesystem")
    new Sync(message => println("  "+message)).run(path)
    println("Create indexes")
    new RecordDao().ensureIndexes
  }
}

class RebuildCommand extends SyncCommand {
  override def run = {
    println("Drop database")
    new RecordDao().drop
    new BandDao().drop
    super.run
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
