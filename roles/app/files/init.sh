#!/bin/sh

APPUSER=devops
APP_HOME=/home/$APPUSER
APP_JAR=current
LOGS=$APP_HOME/logs

PID=$( ps -ea -o "pid ppid args" | grep -v grep | grep "java -jar current" | sed -e 's/^  *//' -e 's/ .*//' | head -1 )

_start() {
  if [ -z $PID ]; then
    echo "Starting devops..."
    cd $APP_HOME
    /bin/su -l $APPUSER -c "nohup java -jar $APP_JAR 1>$LOGS/stdout.log 2>$LOGS/stderr.log &"
    PID=$( ps -ea -o "pid ppid args" | grep -v grep | grep "java -jar devops.jar" | sed -e 's/^  *//' -e 's/ .*//' | head -1 )
    cd -
    echo "Started devops with PID: $PID"
  else
    echo "devops already running"
  fi
}

_stop() {
  echo "Stopping devops..."
  if [ -z $PID ]; then
    echo "devops already stopped"
  else
    kill $PID
    unset PID
  fi
}

_status() {
  test $PID && echo "devops is running with PID: ${PID}" || echo "devops is not running"
}

case "$1" in
  start)
    _start
    ;;
  stop)
    _stop
    ;;
  restart)
    _stop
    _start
    ;;
  status)
    _status
    ;;
  *)
    echo "Usage: $0 {start|stop|restart}"
    ;;
esac
