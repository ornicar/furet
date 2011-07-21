package furet.sync

import java.io.File
import furet.model.Store

class FilesystemWalker(path: String) extends StoreBuilder {
  val regex = """^([\s\w]+)\s\-\s\[(\d+)\]\s\-\s([\s\w]+)$""".r
  val root = new File(path)

  def createStore = {
    store = new Store
    walk(root)
    store
  }
  def walk(dir: File): Unit = {
    analyse(dir)
    dir.listFiles filter (_.isDirectory) foreach walk
  }
  def analyse(dir: File) = {
    dir.getName match {
      case regex(band, year, name) =>
        store.registerRecord(band, year, name, dir.getAbsolutePath)
      case _ =>
    }
  }
}
