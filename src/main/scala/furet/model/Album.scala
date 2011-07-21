package furet.model

import com.novus.salat._
import com.novus.salat.annotations._
import scala.collection.immutable.{Map => IMap}
import scala.collection.mutable.{Map => MMap}
import scala.math.{BigDecimal => ScalaBigDecimal}

case class Album(band: String, year: String, name: String, path: String) {
  override def toString() = band+" "+year+" "+name+" on "+path
}
