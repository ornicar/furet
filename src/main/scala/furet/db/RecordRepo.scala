package furet.db

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

class RecordRepo(collection: MongoCollection) extends Repo(collection) {
  type Model = Record
  val mapper = grater[Record]

  override def find(query: MongoDBObject): Set[Model] =
    collection.find(query).sort(MongoDBObject("tokens" -> -1)).toSet map unserialize

  def search(term: String): Set[Record] = {
    val regex = term.toLowerCase.r
    find(MongoDBObject("tokens" -> regex))
  }

  def index() {
    collection.ensureIndex(
      MongoDBObject("band" -> 1, "year" -> 1, "name" -> 1),
      "unicity", true
    )
    collection ensureIndex MongoDBObject("tokens" -> 1)
  }
}
