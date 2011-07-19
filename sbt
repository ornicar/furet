java -Dfile.encoding=UTF8 -Xmx1024M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m -jar $HOME/data/bin/sbt-launch.jar "$@"
