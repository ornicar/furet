package furet.fs

import furet.model._
import java.io.File

class Renamer(val log: Any => Unit) {
  val missingBracketsRegex = """^(.+)\s\-\s(\d+)\s\-\s(.+)$""".r

  def run(dirs: Set[File]) {
    val misnamedRecords = new Finder(missingBracketsRegex).find(dirs)
    //val renames = for ((dir, record) <- misnamedRecords)
      //yield
    //misnamedRecords foreach println
  }
}
