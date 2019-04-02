#!/bin/sh

PID=`cat log/start.pid`
kill -9 ${PID}

nohup java -Dloader.path=/data/search/springboot-demo/lib/ -jar /data/search/springboot-demo/springboot-demo-0.0.1-SNAPSHOT.jar >>log/out.txt 2>&1 &

echo $! > log/start.pid
