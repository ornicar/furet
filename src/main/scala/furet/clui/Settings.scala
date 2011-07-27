package furet.clui

import furet.Domain
import furet.db.Db
import furet.fs.Fs
import scala.collection.mutable
import java.io.File

// Global settings for the command-line UI
class Settings {
  val PRODUCT: String = "furet"
  val VERSION: String = "0.1.0"
  val COPYRIGHT: String = "WTFPL"
  val versionMsg = PRODUCT + " " + VERSION + " -- " + COPYRIGHT

  // the directories that are being managed
  private[this] var dirs: mutable.Set[File] = mutable.Set()

  // the record regex
  var regex = """^(.+)\s\-\s\[?(\d+)\]?\s\-\s(.+)$""".r

  // whether to actually do the requested work, or to
  // just print out what would be done
  var dryrun = false

  // whether to print out extra information about what
  // the tool is doing
  var verbose = false

  // Creates the domain from selected dirs
  def domain: Domain = {
    val domainDirs = dirs match {
      case d if d.isEmpty => Set(new File("."))
      case d => d.toSet
    }
    new Domain(new Fs(domainDirs, regex), Db())
  }

  /** Parse global options from the beginning of a command-line.
   * Returns the portion of the command line that was not
   * consumed.
   */
  def parseOptions(args: List[String]): List[String] =
    args match {
      case "-d" :: dirname :: rest =>
        this.dirs += new File(dirname)
        parseOptions(rest)

      case ("-d" | "--dir") :: Nil =>
        println("Option -d requires an argument")
        sys.exit(1)

      case ("-n" | "--dry") :: rest =>
        println("*** Dry run ***")
        dryrun = true
        parseOptions(rest)

      case ("-v" | "--verbose") :: rest =>
        verbose = true
        parseOptions(rest)

      case ("-r" | "--regex") :: regexString :: rest =>
        regex = regexString.r
        parseOptions(rest)

      case "-version" :: rest =>
        println(versionMsg)
        sys.exit(0)

      case _ =>
        args
    }

  // describe the global options
  val fullHelp =
    """Global options:
      |
      |   -d <dir>        Operate on dir as the local managed directory.
      |                   Can be applied multiple times for multiple directories.
      |   -n | --dryrun   Do not actually do anything. Only print out what
      |                   tool would normally do with the following arguments.
      |   -v | --verbose  Output detailed messages about what furet is doing.
      |   -r | --regex    Regex used to identify a record in the filesystem
      |   -version        Version information.
      |""".stripMargin
}
