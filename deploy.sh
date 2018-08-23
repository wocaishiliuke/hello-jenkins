#!/usr/bin/env bash
#编译+部署hellojenkins站点

#需要配置如下参数
# 项目路径和名称
#export PROJ_PATH=`pwd`
#export PROJ_NAME=hellojenkins

# 输入你的环境上tomcat的全路径
#export TOMCAT_PATH=/home/yuzhou/top/apache-tomcat-8.5.33

### base 函数
killTomcat()
{
    pid=`ps -ef|grep tomcat|grep java|awk '{print $2}'`
    echo "tomcat Id list :$pid"
    if [ "$pid" = "" ]
    then
      echo "no tomcat pid alive"
    else
      kill -9 $pid
    fi
}
cd $PROJ_PATH/$PROJ_NAME
mvn clean install

# 停tomcat
killTomcat

# 删除原有工程
rm -rf $TOMCAT_PATH/webapps/ROOT
rm -f $TOMCAT_PATH/webapps/ROOT.war
rm -f $TOMCAT_PATH/webapps/$PROJ_NAME.war

# 复制新的工程
cp $PROJ_PATH/$PROJ_NAME/target/$PROJ_NAME.war $TOMCAT_PATH/webapps/
cd $TOMCAT_PATH/webapps/
mv hellojenkins.war ROOT.war

# 启动Tomcat
cd $TOMCAT_PATH/
sh bin/startup.sh
