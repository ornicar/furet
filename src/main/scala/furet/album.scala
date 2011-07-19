import com.mongodb.casbah.Imports._

class AlbumCollection {
  var collection = MongoConnection("localhost")("furet")("album")
}
