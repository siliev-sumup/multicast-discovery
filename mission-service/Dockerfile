FROM openjdk:13-jdk-alpine

ARG PROFILE
ENV PROFILE=$PROFILE
RUN sh -c 'echo Building with profile: "$PROFILE"'

RUN apk update && \
    apk upgrade && \
    apk --no-cache add netcat-openbsd && \
    apk --no-cache add curl &&\
    apk --no-cache add mysql-client &&\
    apk --no-cache add mariadb-client

RUN addgroup -S spring && \
    adduser -S -G spring spring

ADD run.sh run.sh
RUN chown spring run.sh
RUN chmod +x run.sh

EXPOSE 8080

USER spring

ADD target/mission*.jar /usr/local/
CMD ./run.sh
