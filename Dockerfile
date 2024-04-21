FROM maven:3.6-openjdk-17-slim as build-stage

ARG PROJECT_DIR=/home/app

CMD mkdir -p /var/logs

RUN mkdir -p $PROJECT_DIR

WORKDIR $PROJECT_DIR

COPY pom.xml mvnw* $PROJECT_DIR/
# TODO load dependencies once
RUN mvn dependency:resolve

COPY . $PROJECT_DIR

CMD mvn spring-boot:run

EXPOSE 8081