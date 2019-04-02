#!/bin/sh
PID=`cat unsubscribe.pid`
kill -9 $PID
echo "stop unsubscribe job ${PID} success"