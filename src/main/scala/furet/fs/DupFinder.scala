package furet.fs

import furet.model._
import java.io.File
import collection.mutable.{Set => MutableSet}

class DupFinder(fs: Fs) {

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
