#!/bin/sh
# 执行demo：sh script/publish.sh 127.0.0.1 false/true

isCpLib=false
paramCount=$#
if paramCount==2; then
    isCpLib=$2
fi

#检查IP是否合法
function check_ip()
{
    echo $1|grep "^[0-9]\{1,3\}\.\([0-9]\{1,3\}\.\)\{2\}[0-9]\{1,3\}$" > /dev/null;
    if [ $? -ne 0 ]
    then
        echo "IP地址必须全部为数字"
        return 1
    fi
    ip=$1
    a=`echo ${ip}|awk -F . '{print $1}'`  #以"."分隔，取出每个列的值
    b=`echo ${ip}|awk -F . '{print $2}'`
    c=`echo ${ip}|awk -F . '{print $3}'`
    d=`echo ${ip}|awk -F . '{print $4}'`
    for num in ${a} ${b} ${c} ${d}
    do
        if [ ${num} -gt 255 ] || [ ${num} -lt 0 ]    #每个数值必须在0-255之间
        then
            echo ${ip} "中，字段"${num}"错误"
            return 1
        fi
   done
   echo ${ip} "地址合法"
   return 0
}

if [ -n "$1" ] ;then
    echo "start publish jar to '$1'"
    ip=$1
    check_ip ${ip}
    cd /Users/wugang/workspace/java/springboot-demo;
    mvn clean
    echo "maven clean done."
    sleep 1s
    mvn install -Dmaven.test.skip=true
    echo "maven install done."
    sleep 2s
    #解决java.lang.SecurityException: Invalid signature file digest for Manifest main attributes问题
#    zip -d target/product-recommend-0.0.1-SNAPSHOT.jar 'META-INF/.SF' 'META-INF/.RSA' 'META-INF/*SF'

    echo ${isCpLib}
    if [[ ${isCpLib} == 'true' ]] ; then
        scp -r target/lib search@${ip}:/data/search/springboot-demo
        echo "成功发布lib文件到目标机器:/data/search/springboot-demo"
    fi

    scp target/springboot-demo-0.0.1-SNAPSHOT.jar search@${ip}:/data/search/springboot-demo
    echo "publish jar to '${ip}' done."
else
    echo "请输入publish的目标机器IP"
fi