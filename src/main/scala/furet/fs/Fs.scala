package furet.fs

import furet.model.Store
import scala.util.matching.Regex
import java.io.File

class Fs(val dirs: Set[File], val regex: Regex) {

  def find: Set[FsRecord] = walkDirs(dirs)

  def makeStore: Store = {
    val records = find map (_.record)
    val bands = records map (_.band)
    new Store(bands, records)
  }

  private[this] def walkDirs(ds: Set[File]): Set[FsRecord] =
    ds filter (_.isDirectory) flatMap { dir =>
      dir.getName match {
        case regex(band, year, name) =>
          Set(FsRecord(dir, band, year, name))
        case _ => walkDirs(dir.listFiles.toSet)
      }
    }
}
