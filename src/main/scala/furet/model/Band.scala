package furet.model

import com.novus.salat._
import com.novus.salat.annotations._

case class Band(
  name: String
) {
  override def toString() = name
}

class BandSet extends scala.collection.mutable.HashSet[Band] {
}
