#!/bin/sh

python monitor_trip.py /data/search/springboot-demo/subscribe/out.txt /data/search/springboot-demo/subscribe/last_position.txt
#crontab job
##monitor subscribe trip by@wugang
#*/1 * * * * (cd /data/search/wugang/monitor; sh monitor_subscribe.sh)