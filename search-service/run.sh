#!/bin/sh

echo "********************************************************"
echo "Starting Search Service"
echo "Using Profile: $PROFILE"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom \
     -Dspring.profiles.active=$PROFILE \
     -jar /usr/local/search-service-0.0.1-SNAPSHOT.jar
