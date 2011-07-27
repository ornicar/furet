package furet.fs

import furet.model._
import java.io.File

case class FsRecord(val dir: File, val record: Record) {
}

object FsRecord {
  def apply(dir: File, band: String, year: String, name: String): FsRecord =
    new FsRecord(dir, new Record(new Band(band), year, name))
}
