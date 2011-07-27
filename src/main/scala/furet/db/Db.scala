package furet.db

import furet.model._
import com.mongodb.casbah.Imports._

class Db(val bandRepo: BandRepo, val recordRepo: RecordRepo) {
  val repos: List[Repo] = List(bandRepo, recordRepo)

  def makeStore(): Store =
    new Store(bandRepo.findAll, recordRepo.findAll)

  def index() { repos foreach (_.index) }

  def drop() { repos foreach (_.drop) }
}

object Db {
  val defaultHost = "localhost"
  val defaultDbName = "furet"

  def apply(host: String, dbName: String): Db = {
    val db = MongoConnection(host)(dbName)
    new Db(new BandRepo(db("band")), new RecordRepo(db("record")))
  }

  def apply(): Db = apply(defaultHost, defaultDbName)
}
