#!/bin/sh
PID=`cat /data/search/springboot-demo/subscribe/subscribe.pid`
kill -9 $PID
# 指定日志配置文件
nohup /data/search/spark/bin/spark-submit --class com.bubble.job.consumer.SubscribeConsumer --master spark://127.0.0.1:7077  --deploy-mode client --driver-memory 2g --total-executor-cores 20 --conf spark.io.port=5000 /data/search/springboot-demo/springboot-demo-1.0-SNAPSHOT.jar 1000 >/data/search/springboot-demo/subscribe/out.txt 2>&1 &
echo $! > /data/search/springboot-demo/subscribe/subscribe.pid