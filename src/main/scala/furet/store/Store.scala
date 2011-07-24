package furet.store

import furet.model._

abstract class Store(
  val bands: Set[Band],
  val records: Set[Record]
) {
  override def toString() = {
    bands.size + " bands, " + records.size + " records"
  }
}
