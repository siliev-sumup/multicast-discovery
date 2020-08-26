#!/bin/sh

export AUTH_DB_HOST=auth-db
export AUTH_DB_PORT=3306

echo "Starting with profile $PROFILE"

until nc -z $AUTH_DB_HOST "$AUTH_DB_PORT"
do
    echo "********************************************************"
    echo "Waiting for Auth DB $AUTH_DB_PORT"
    echo "********************************************************"
    sleep 5;
done
echo "******* Auth DB has started"

echo "********************************************************"
echo "Starting Authentication Service"
echo "Using Profile: $PROFILE"
echo "********************************************************"

java -Djava.security.egd=file:/dev/./urandom \
     -Dspring.profiles.active="$PROFILE" \
     -jar /usr/local/authentication-service-0.0.1-SNAPSHOT.jar
