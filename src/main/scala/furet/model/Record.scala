package furet.model

case class Record private (
  band: Band,
  year: String,
  name: String,
  tokens: String
) {
  override def toString = {
    band + " - [" + year + "] - " + name
  }
}

object Record {
  def apply(band: Band, year: String, name: String) = {
    val tokens = tokenize(band, year, name)
    new Record(band, year, name, tokens)
  }

  private def tokenize(band: Band, year: String, name: String): String =
    (name + " " + year + " " + band.name).toLowerCase
}
