package furet.sync

import java.io.File
import furet.model._
import furet.dao._
import furet.RecursiveFilesystemIterator

class Sync {
  val dirs = "/home/thib/data/Music" :: Nil map (new File(_))
  val regex = """^(\w+)\s\-\s\[(\d+)\]\s\-\s(\w+)$""".r
  val albumDao = new AlbumDao
  var albums: List[Album] = List()
  def sync {
    albumDao.drop
    dirs foreach ( dir =>
      new RecursiveFilesystemIterator(syncDir) iterateDirs dir
    )

    albums foreach (albumDao save _)
  }
  def syncDir(dir: File) = {
    dir.getName match {
      case regex(band, year, name) =>
        albums = new Album(band, year, name, dir.getAbsolutePath) :: albums
      case _ =>
    }
  }
}
