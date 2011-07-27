package furet

import furet.fs._
import furet.db._
import furet.model._
import furet.sync._
import java.io.File

// Managed part of a filesystem
class Domain(val dirs: Set[File], val db: Db) {
  def fsStore: Store = StoreBuilder(dirs)
  def dbStore: Store = new Store(db.bandRepo.findAll, db.recordRepo.findAll)
  def sync() {
    // Make sure the database indexes are set
    db.index()
    // Add bands
    // operate(fs.bands -- db.bands, BandRepo.save, "Add missing bands")
    (fsStore.bands -- dbStore.bands) foreach db.bandRepo.save
    // Add records
    (fsStore.records -- dbStore.records) foreach db.recordRepo.save

    // Records that are in DB but not in FS
    val missings = dbStore.records -- fsStore.records
  }
}
