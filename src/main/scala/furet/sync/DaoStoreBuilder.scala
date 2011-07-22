package furet.sync

import furet.dao._
import furet.model.Store

class DaoStoreBuilder extends StoreBuilder {
  def createStore = {
    store = new Store
    BandDao.findAll foreach store.bands.add
    RecordDao.findAll foreach (store.records += _)
    store
  }
}
