package furet.sync

import furet.dao._
import furet.model.Store

class DaoStoreBuilder extends StoreBuilder {
  def createStore = {
    store = new Store
    new BandDao().findAll foreach store.bands.add
    new RecordDao().findAll foreach (store.records += _)
    store
  }
}
