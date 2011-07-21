package furet.dao

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

class AlbumDao {
  val collection = MongoConnection("localhost")("furet")("album");

  def findAll: Array[Album] = collection.toArray map unserialize
  def drop = collection remove MongoDBObject.empty
  def save(album: Album) = collection insert serialize(album)

  def unserialize(dbo: DBObject): Album = grater[Album] asObject dbo
  def serialize(album: Album): DBObject = grater[Album] asDBObject album
}
