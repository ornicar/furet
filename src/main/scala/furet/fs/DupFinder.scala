package furet.fs

import furet.model._
import collection.mutable.{HashMap, ListBuffer}
import java.io.File

class DupFinder(val log: Any => Unit) {
  def run(dirs: Set[File]) {
    val records = new Finder(Config.regex).find(dirs)
    val dups = dirsByRecord(records)

    //for (dup in dups) {
      //println()
      //println("* " + dup._1)
      //dup._2 foreach println
    //}
  }
  def dirsByRecord(records: List[(File, Record)]):
    HashMap[Record, ListBuffer[File]] = {
    val map = HashMap[Record, ListBuffer[File]]()
    for ((dir, record) <- records) {
      if (map.contains(record)) map(record) += dir
      else map(record) = ListBuffer(dir)
    }
    map filter (_._2.length > 1)
  }
}
