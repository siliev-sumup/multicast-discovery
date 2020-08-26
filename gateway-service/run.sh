#!/bin/sh

echo "********************************************************"
echo "Starting Gateway"
echo "Using Profile: $PROFILE"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom \
     -Dspring.profiles.active="$PROFILE" \
     -jar /usr/local/gateway-service-0.0.1-SNAPSHOT.jar
