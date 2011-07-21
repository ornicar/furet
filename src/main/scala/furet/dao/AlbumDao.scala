package furet.dao

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO

class AlbumDao {
  val collection = MongoConnection("localhost")("furet")("album");
  def findAll: Array[Album] = collection.toArray map hydrate
  def drop = collection remove MongoDBObject.empty
  def save(album: Album) = collection insert serialize(album)
  def hydrate(dbo: DBObject): Album = grater[Album] asObject dbo
  def serialize(album: Album): DBObject = grater[Album] asDBObject album
}
