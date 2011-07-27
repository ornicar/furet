package furet

import furet.fs._
import furet.db._
import furet.model._
import furet.sync._
import java.io.File

// Managed part of a filesystem
class Domain(val fs: Fs, val db: Db) {

  def sync() {
    val (fsStore, dbStore) = (fs.makeStore, db.makeStore)

    // Make sure the database indexes are set
    db.index()

    // Add bands
    (fsStore.bands -- dbStore.bands) foreach db.bandRepo.save

    // Add records
    (fsStore.records -- dbStore.records) foreach db.recordRepo.save

    // Records that are in DB but not in FS
    val missings = dbStore.records -- fsStore.records
  }

  def findDup = new DupFinder(fs).find
}
