package furet.fs

import java.io.File
import furet.store._
import furet.model._

object StoreBuilder {
  def apply(dirs: Set[File]): Store = {
    val records = walkDirs(dirs.toList).toSet
    val bands = records map (_.band)
    new Store(bands, records)
  }
  private def walkDir(dir: File): List[Record] = {
    dir.getName match {
      case Config.regex(band, year, name) =>
        List(new Record(new Band(band), year, name))
      case _ => walkDirs(dir.listFiles.toList)
    }
  }
  private def walkDirs(dirs: List[File]): List[Record] =
    dirs filter (_.isDirectory) flatMap walkDir
}
