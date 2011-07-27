package furet.fs

import furet.model._
import collection.mutable.{Map => MutableMap, Set => MutableSet}
import java.io.File

class DupFinder(fs: Fs) {
  type RecordDirs = MutableMap[Record, MutableSet[File]]
  object RecordDirs {
    def apply() = MutableMap[Record, MutableSet[File]]()
  }

  def find: RecordDirs = dirsByRecord(fs.find)

  def dirsByRecord(fsRecords: Set[FsRecord]): RecordDirs = {
    val map = RecordDirs()
    for (fsRecord <- fsRecords) {
      val (record, dir) = (fsRecord.record, fsRecord.dir)
      if (map.contains(record)) map(record) += dir
      else map(record) = MutableSet(dir)
    }
    map filter (_._2.size > 1)
  }
}
