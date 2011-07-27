package furet

import furet.fs._
import furet.db._
import furet.model._

class Domain(val fs: Fs, val db: Db) {
  def sync() {
    db.index()
    val (fsStore, dbStore) = (fs.makeStore, db.makeStore)
    (fsStore.bands -- dbStore.bands) foreach db.bandRepo.save
    (dbStore.bands -- fsStore.bands) foreach db.bandRepo.remove
    (fsStore.records -- dbStore.records) foreach db.recordRepo.save
    (dbStore.records -- fsStore.records) foreach db.recordRepo.remove
  }

  def findDup: RecordDirs = new DupFinder(fs).find

  def search(term: String): Set[Record] = db.recordRepo.search(term)
}
