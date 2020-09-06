#!/bin/sh

export DB_HOST=nummi-db
export DB_PORT=3306

echo "Starting with profile $PROFILE"

until nc -z $DB_HOST "$DB_PORT"
do
    echo "********************************************************"
    echo "Waiting for Auth DB $DB_PORT"
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
