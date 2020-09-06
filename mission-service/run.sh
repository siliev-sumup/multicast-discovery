#!/bin/sh

export DB_HOST=nummi-db
export DB_PORT=3306

echo "Starting with profile $PROFILE"

if [ ! "$PROFILE" = "stage" ]; then
    until nc -z $DB_HOST "$DB_PORT"
    do
        echo "********************************************************"
        echo "Waiting for DB on $DB_PORT"
        echo "********************************************************"
        sleep 5;
    done
    echo "******* DB has started"
fi

echo "********************************************************"
echo "Starting Mission Service"
echo "Using Profile: $PROFILE"
echo "********************************************************"

java -Djava.security.egd=file:/dev/./urandom \
     -Dspring.profiles.active="$PROFILE" \
     -jar /usr/local/mission-service-0.0.1-SNAPSHOT.jar