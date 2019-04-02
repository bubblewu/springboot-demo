#!/bin/sh
cd /data/search/springboot-demo/subscribe/
sh stop_trip_subscribe.sh
echo "start run subscribe."
sh run_trip_subscribe.sh

sleep 2
cd /data/search/springboot-demo/unsubscribe/
sh stop_trip_unsubscribe.sh
echo "start run unsubscribe."
sh run_trip_unsubscribe.sh