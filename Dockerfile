# Use a Java 17 base image
FROM bellsoft/liberica-openjdk-alpine:21

# Maintainer information
MAINTAINER emiralya8
ARG JAR_FILE=target/*.jar

# Copy the application JAR file to the container
COPY ${JAR_FILE} springConference.jar
# COPY springConference-0.0.1-SNAPSHOT.jar springConference.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/springConference.jar"]


#FROM openjdk:11-jre-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
