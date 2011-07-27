package furet.db

import furet.model._
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

class BandRepo(collection: MongoCollection) extends Repo(collection) {
  type Model = Band
  val mapper = grater[Band]

  def index() {
    collection ensureIndex MongoDBObject("name" -> 1)
  }
}
