#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @File  : monitor_trip.py
# @Author: wu gang
# @Date  : 2019/3/14
# @Desc  : 监控trip-rec项目的日志:
# python /data/search/wugang/monitor/monitor_trip.py /data/search/trip-rec/subscribe/out.txt /data/search/trip-rec/subscribe/last_position.txt
# @Contact: 752820344@qq.com
import os
import re
from os.path import getsize
from re import IGNORECASE
import sys
from sendmail import sendmail

mailto = 'xxx@111.cn'
subject = 'Consumer Trip Error'
# logfile = '/Users/wugang/data/logs/springboot-demo/springboot-demo-error.log'
# 该文件是用于记录上次读取日志文件的位置,执行脚本的用户要有创建该文件的权限
# last_position_logfile = 'last_position.txt'

# 匹配的错误信息关键字的正则表达式
pattern = re.compile(r'Exception|^\t+\bat\b', IGNORECASE)


# 读取上一次日志文件的读取位置
def get_last_position(file):
    try:
        data = open(file, 'r')
        last_position = data.readline()
        if last_position:
            last_position = int(last_position)
        else:
            last_position = 0
    except:
        last_position = 0

    return last_position


# 写入本次日志文件的本次位置
def write_this_position(file, last_position):
    try:
        data = open(file, 'w')
        data.write(str(last_position))
        data.write('\n Notice: \n' + "Don't delete this file,It is very important for looking error Log !!! \n")
        data.close()
    except:
        print("Can't create file :", file)
        exit()


# 分析文件找出异常的行
def analysis_log(file, last_position_logfile):
    error_list = []
    try:
        log = open(file, 'r')
    except:
        exit()

    last_position = get_last_position(last_position_logfile)
    this_position = getsize(logfile)
    if this_position < last_position:
        log.seek(0)
    elif this_position == last_position:
        exit()
    elif this_position > last_position:
        log.seek(last_position)

    for line in log:
        # if 'Exception' in line:
        if pattern.search(line):
            error_list.append(line)

    write_this_position(last_position_logfile, log.tell())
    log.close()
    return ''.join(error_list)


def monitor():
    errorCount = os.popen(
        'grep -E "Caused by|Exception|ERROR" /Users/wugang/data/logs/springboot-demo/springboot-demo-error.log | '
        'wc -l').read()
    if int(errorCount) != 0:
        data = os.popen(
            'grep -E "Caused by|Exception|ERROR" /Users/wugang/data/logs/springboot-demo/springboot-demo-error.log').read()
        sendmail(mailto, data, subject)
        print("error count:", errorCount)
    else:
        print("no error.")


if __name__ == '__main__':
    # monitor()
    (logfile, last_position_logfile) = (sys.argv[1], sys.argv[2])
    error_info = analysis_log(logfile, last_position_logfile)
    data = "--- ERROR File: ---\n" + logfile + "\n --- ERROR Info: --- \n" + error_info
    if error_info:
        sendmail(mailto, error_info, subject)
        # print(data)
