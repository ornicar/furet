package furet.fs

import furet.model._
import java.io.File

class Renamer(fs: Fs) {
  val missingBracketsRegex = """^(.+)\s\-\s(\d+)\s\-\s(.+)$""".r

  def run() {
    //val misnamedRecords = finder(missingBracketsRegex).find(dirs)
    //val renames = for ((dir, record) <- misnamedRecords)
      //yield
    //misnamedRecords foreach println
  }
}
