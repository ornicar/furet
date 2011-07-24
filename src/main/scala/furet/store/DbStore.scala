package furet.store

import furet.model._
import furet.dao._

class DbStore(
  bands: Set[Band], records: Set[Record]
) extends Store(bands, records)

object DbStore {
  def create = new DbStore(
    BandDao.findAll.toSet,
    RecordDao.findAll.toSet
  )
}
