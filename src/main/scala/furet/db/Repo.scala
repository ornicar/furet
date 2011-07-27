package furet.db

import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

abstract class Repo(val collection: MongoCollection) {
  type Model <: CaseClass
  val mapper: Grater[Model]

  def index(): Unit

  def drop() { collection remove MongoDBObject.empty }
  def save(obj: Model) { collection insert serialize(obj) }
  def findAll: Set[Model] = collection.toSet map unserialize
  def unserialize(dbo: DBObject): Model = mapper asObject dbo
  def serialize(obj: Model): DBObject = mapper asDBObject obj
}
