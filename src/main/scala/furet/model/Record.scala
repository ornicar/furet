package furet.model

import com.novus.salat._
import com.novus.salat.annotations._

case class Record(
  band: Band,
  year: String,
  name: String,
  path: String
) {
  override def toString() = band+" ["+year+"] "+name
}

class RecordSet extends scala.collection.mutable.HashSet[Record] {
}
