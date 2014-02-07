#!/bin/sh


# Alle filer ligger under hjemmekatalog for å gjøre det enklere i starten :)
APP_HOME=/home/devops
APP_JAR=devops.jar
LOGS=/home/devops/logs

PID=$( ps -ea -o "pid ppid args" | grep -v grep | grep "java -jar devops.jar" | sed -e 's/^  *//' -e 's/ .*//' | head -1 )

case "$1" in
  start)
    if [ -z $PID ]; then
      echo "Starting devops..."
      mkdir -p $LOGS
      cd $APP_HOME
      nohup java -jar "$APP_JAR" 1>$LOGS/stdout.log 2>$LOGS/stderr.log &
      PID=$!
      cd -
      echo "Started devops with PID: ${PID}"
    else
      echo "devops already running"
    fi
    ;;
  stop)
    echo "Stopping devops..."
    if [ $PID ]; then
      test -z $PID || kill $PID
    else
      echo "devops already stopped"
    fi
    ;;
  restart)
    $0 stop
    $0 start
    ;;
  *)
    echo "Usage: $0 {start|stop|restart}"
    ;;
esac
