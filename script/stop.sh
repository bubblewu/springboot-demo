#!/bin/sh
PID=`cat log/start.pid`
kill -9 ${PID}
echo "job has stop."