package furet.sync

import furet.model._
import furet.dao._

class Sync(log: String => Unit) {
  def run(fsPath: String) = {
    val db = new DaoStoreBuilder().createStore
    log("Database:   " + db)
    val fs = new FilesystemWalker(fsPath).createStore
    log("Filesystem: " + fs)

    // Add bands
    fs.bands filterNot db.bands.contains foreach addBand
    // Add records
    fs.records filterNot db.records.contains foreach addRecord
  }
  def addBand(band: Band): Unit = {
    log("+ band " + band)
    BandDao.save(band)
  }
  def addRecord(record: Record): Unit = {
    log("+ record " + record)
    RecordDao.save(record)
  }
}
