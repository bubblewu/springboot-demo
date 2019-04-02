#!/bin/sh
PID=`cat subscribe.pid`
kill -9 $PID
echo "stop subscribe job ${PID} success"