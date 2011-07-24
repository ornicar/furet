package furet.dao

import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import scala.collection.mutable.ArraySeq

abstract class Dao[Model <: CaseClass] {
  val collection: MongoCollection
  val mapper: Grater[Model]

  def index(): Unit

  def drop() {
    collection remove MongoDBObject.empty
    this.index()
  }
  def save(obj: Model) { collection insert serialize(obj) }
  def findAll: List[Model] = collection.toList map unserialize
  def unserialize(dbo: DBObject): Model = mapper asObject dbo
  def serialize(album: Model): DBObject = mapper asDBObject album
}
