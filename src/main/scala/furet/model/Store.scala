package furet.model

class Store(
  val bands: Set[Band],
  val records: Set[Record]
) {
  override def toString() =
    "%d bands, %d records".format(bands.size, records.size)
}
