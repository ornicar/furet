package furet.model

case class Record(
  band: Band,
  year: String,
  name: String
) {
  override def toString = {
    band + " - [" + year + "] - " + name
  }
}
