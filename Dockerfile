#Common Docker keywords
#-FROM: pull from a base image that already exists
#-COPY: copy files / folders from your machine into the image
#-RUN: execute a bash command
#-EXPOSE: expose a port on the container

# build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /home/app/pom.xml
COPY src/ /home/app/src/

RUN mvn -f home/app/pom.xml clean package

# run stage
FROM openjdk:11-jre-slim

COPY --from=build /home/app/target/grocerylistapp-1.0-SNAPSHOT-jar-with-dependencies.jar /home/app/app.jar

# Open port for use
EXPOSE 9000

ENTRYPOINT ["java", "-jar", "home/app/app.jar"]