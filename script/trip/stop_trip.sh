#!/bin/sh
echo "stop run subscribe."
cd /data/search/springboot-demo/subscribe/
sh stop_trip_subscribe.sh

echo "stop run unsubscribe."
cd /data/search/springboot-demo/unsubscribe/
sh stop_trip_unsubscribe.sh