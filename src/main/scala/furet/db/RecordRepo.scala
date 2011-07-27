package furet.db

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

class RecordRepo(collection: MongoCollection) extends Repo(collection) {
  type Model = Record
  val mapper = grater[Record]

  def index() {
    collection.ensureIndex(
      MongoDBObject("band" -> 1, "year" -> 1, "name" -> 1),
      "unicity", true
    )
  }
}
