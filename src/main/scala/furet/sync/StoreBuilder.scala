package furet.sync

import furet.model.Store

trait StoreBuilder {
  var store = new Store
  def createStore: Store
}
