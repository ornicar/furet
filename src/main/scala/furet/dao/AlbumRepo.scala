package furet.dao

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO

class AlbumRepo {
  val collection = MongoConnection("localhost")("furet")("album");

  def findAll: Array[Album] = collection.toArray map hydrate

  def hydrate(dbo: DBObject): Album = grater[Album].asObject(dbo)
}
