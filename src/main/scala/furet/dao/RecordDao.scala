package furet.dao

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

class RecordDao {
  val collection = MongoConnection("localhost")("furet")("album");

  def findAll: Array[Record] = collection.toArray map unserialize
  def drop = collection remove MongoDBObject.empty
  def save(album: Record) = collection insert serialize(album)

  def unserialize(dbo: DBObject): Record = grater[Record] asObject dbo
  def serialize(album: Record): DBObject = grater[Record] asDBObject album

  def ensureIndexes = {
    collection ensureIndex MongoDBObject("band" -> 1)
    collection ensureIndex MongoDBObject("name" -> 1)
    collection ensureIndex MongoDBObject("year" -> 1)
  }
}
