package furet.model

class Store {
  val bands = new BandSet
  val records = new RecordSet

  def registerRecord(bandName: String, year: String, name: String, path: String) = {
    val record = new Record(registerBand(bandName), year, name, path);
    records += record
    record
  }
  def registerBand(name: String): Band = {
    val band = new Band(name)
    if (!bands.contains(band))
      bands add band
    band
  }
  override def toString() = {
    bands.size + " bands, " + records.size + " records"
  }
}
