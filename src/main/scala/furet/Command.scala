package furet

import furet.dao._
import furet.sync._

abstract class Command {
  def run: Unit
}

class ListCommand extends Command {
  override def run {
    RecordDao.findAll foreach println
  }
}

class SyncCommand extends Command {
  val path = "/home/thib/data/Music"
  override def run {
    println("Create indexes")
    RecordDao.index
    BandDao.index
    println("Synchronize database with filesystem")
    new Sync(m => println("  "+m)).run(path)
  }
}

class DropCommand extends Command {
  override def run {
    println("Drop database")
    RecordDao.drop
    BandDao.drop
  }
}

class HelpCommand extends Command {
  override def run {
    println("""Available commands:
      |furet sync         Update the database, synchronizing it with the filesystem
      |furet drop         Drop the database
      |furet help         Display this help""".stripMargin)
  }
}

class NotFoundCommand extends HelpCommand {
  override def run {
    println("This is not a valid furet command")
    super.run
  }
}
