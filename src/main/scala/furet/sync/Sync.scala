package furet.sync

import furet.model._
import furet.store._
import furet.dao._

class Sync(log: String => Unit) {
  def run(fsPath: String) = {
    val db = DbStore.create
    log("Database:   " + db)
    val fs = FsStore.create
    log("Filesystem: " + fs)

    // Add bands
    fs.bands filterNot db.bands.contains foreach BandDao.save
    // Add records
    fs.records filterNot db.records.contains foreach RecordDao.save
  }
}
