package furet.dao

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

object RecordDao extends Dao[Record] {
  val collection = MongoConnection("localhost")("furet")("album");
  val mapper: Grater[Record] = grater[Record]

  def index() = {
    collection.ensureIndex(
      MongoDBObject("band" -> 1, "year" -> 1, "name" -> 1),
      "unicity", true
    )
  }
}
