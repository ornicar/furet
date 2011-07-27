package furet.fs

import furet.model._
import scala.util.matching.Regex
import java.io.File

class Finder(val regex: Regex) {

  def find(dirs: Set[File]): List[(File, Record)] =
    walkDirs(dirs.toList)

  private[this] def walkDirs(dirs: List[File]): List[(File, Record)] =
    dirs filter (_.isDirectory) flatMap { dir =>
      dir.getName match {
        case regex(band, year, name) =>
          List((dir, new Record(new Band(band), year, name)))
        case _ => walkDirs(dir.listFiles.toList)
      }
    }
}
