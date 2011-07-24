package furet.store

import java.io.File
import scala.collection.mutable
import furet.model._

class FsStore(
  bands: Set[Band], records: Set[Record]
) extends Store(bands, records)

object FsStore {
  val regex = """^(.+)\s\-\s\[(\d+)\]\s\-\s(.+)$""".r
  val dirs = List(new File("/home/thib/data/Music"))

  def create = {
    val records = walkDirs(dirs)
    val bands = records map (_.band)
    new FsStore(bands.toSet, records.toSet)
  }
  def walkDir(dir: File): List[Record] = {
    dir.getName match {
      case regex(band, year, name) =>
        List(new Record(new Band(band), year, name))
      case _ => walkDirs(dir.listFiles.toList)
    }
  }
  def walkDirs(dirs: List[File]): List[Record] =
    dirs filter (_.isDirectory) flatMap walkDir
}
