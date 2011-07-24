package furet.dao

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

object BandDao extends Dao[Band] {
  val collection = MongoConnection("localhost")("furet")("band")
  val mapper: Grater[Band] = grater[Band]

  def index() = {
    collection ensureIndex MongoDBObject("name" -> 1)
  }
}
