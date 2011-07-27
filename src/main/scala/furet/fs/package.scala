package furet

import furet.model._
import java.io.File
import collection.mutable.{Map => MutableMap, Set => MutableSet}

package object fs {
  type RecordDirs = MutableMap[Record, MutableSet[File]]

  def RecordDirs() = MutableMap[Record, MutableSet[File]]()
}
