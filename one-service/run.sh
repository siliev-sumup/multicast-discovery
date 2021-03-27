#!/bin/sh

echo "********************************************************"
echo "Starting One Service"
echo "Using Profile: $PROFILE"
echo "********************************************************"

java -Djava.security.egd=file:/dev/./urandom \
     -Dspring.profiles.active="$PROFILE" \
     -jar /usr/local/one-service-0.0.1-SNAPSHOT.jar