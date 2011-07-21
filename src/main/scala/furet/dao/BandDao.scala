package furet.dao

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

class BandDao {
  val collection = MongoConnection("localhost")("furet")("band");

  def findAll: Array[Band] = collection.toArray map unserialize
  def drop = collection remove MongoDBObject.empty
  def save(band: Band) = collection insert serialize(band)

  def unserialize(dbo: DBObject): Band = grater[Band] asObject dbo
  def serialize(band: Band): DBObject = grater[Band] asDBObject band

  def ensureIndexes = {
    collection ensureIndex MongoDBObject("name" -> 1)
  }
}
