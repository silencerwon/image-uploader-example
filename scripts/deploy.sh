#!/bin/bash

USER_HOME=/home/ec2-user
DOWNLOAD_PATH=$USER_HOME/downloads
DEPLOY_PATH=$USER_HOME/deploy
PROJECT_NAME=image-api
INTERFACE_MODULE_NAME=image-api-interface

echo "> 1. Copy application jar files"
cp $DOWNLOAD_PATH/$INTERFACE_MODULE_NAME/build/libs/*.jar $DEPLOY_PATH/

echo "> 2. Check current pid"
CURRENT_PID=$(pgrep -fl ${PROJECT_NAME} | grep jar | awk '{print $1}')
echo "Current pid is ${CURRENT_PID}"

if [ -z "$CURRENT_PID" ]; then
        echo "No running process found."
else
        echo "kill -15 $CURRENT_PID"
        kill -15 "$CURRENT_PID"
        sleep 5
fi

echo "> 3. Start new application"

JAR_NAME=$(ls -tr $DEPLOY_PATH/*.jar | tail -n 1)

echo "JAR Name: $JAR_NAME"
echo "chmod +x $JAR_NAME"
chmod +x $JAR_NAME

echo "execute $JAR_NAME"
nohup java -jar $DEPLOY_PATH/"$JAR_NAME" 2>&1 &