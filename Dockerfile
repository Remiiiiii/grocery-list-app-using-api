#Common Docker keywords
#-FROM: pull from a base image that already exists
#-COPY: copy files / folders from your machine into the image
#-RUN: execute a bash command
#-EXPOSE: expose a port on the container
#-docker build -t <tagname> .: builds the image from a docker file
#-docker run -d -p 9000:9000 <image>
#-sudo docker ps -a: displays all containers
#-sudo docker images: displays all docker images
#-sudo docker rm <first 4 characters of docker container ID>: removes docker container
#-sudo docker stop <first 4 characters of docker container ID>: stops docker container from running
#-sudo docker start <first 4 characters of docker container ID>: starts docker container

# build stage
# FROM maven:3.6.0-jdk-11-slim AS build
# COPY pom.xml /home/app/pom.xml
# COPY src/ /home/app/src/

# RUN mvn -f home/app/pom.xml clean package

# run stage
FROM openjdk:11.0.11-jre-slim

COPY --from=build /home/app/target/grocerylistapp-1.0-SNAPSHOT-jar-with-dependencies.jar /home/app/app.jar

# Open port for use
EXPOSE 9000

ENTRYPOINT ["java", "-jar", "home/app/app.jar"]