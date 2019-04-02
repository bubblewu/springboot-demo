#!/bin/sh
PID=`cat /data/search/springboot-demo/unsubscribe/unsubscribe.pid`
kill -9 $PID
# 指定日志配置文件
#nohup /data/search/spark/bin/spark-submit --class com.bubble.job.consumer.UnSubscribeConsumer --master spark://127.0.0.1:7077  --deploy-mode client --driver-memory 2g --total-executor-cores 20 --files /data/search/springboot-demo/log/log4j2.xml --conf "spark.driver.extraJavaOptions=-Dlog4j.configuration=file:/data/search/springboot-demo/log/log4j2.xml" --conf "spark.executor.extraJavaOptions=-Dlog4j.configuration=file:/data/search/springboot-demo/log/log4j2.xml" --conf spark.io.port=5000 /data/search/springboot-demo/springboot-demo-1.0-SNAPSHOT.jar 1000 &
nohup /data/search/spark/bin/spark-submit --class com.bubble.job.consumer.UnSubscribeConsumer --master spark://127.0.0.1:7077  --deploy-mode client --driver-memory 2g --total-executor-cores 20 --conf spark.io.port=5000 /data/search/springboot-demo/trip-rec-1.0-SNAPSHOT.jar 1000 >/data/search/springboot-demo/unsubscribe/out.txt 2>&1 &

echo $! > /data/search/springboot-demo/unsubscribe/unsubscribe.pid