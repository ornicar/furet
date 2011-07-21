package furet

import java.io.File

class RecursiveFilesystemIterator(callback: File => Unit) {
  def iterateDirs(dir: File): Unit = {
    callback(dir)
    dir.listFiles filter (_.isDirectory) foreach (iterateDirs(_))
  }
}
