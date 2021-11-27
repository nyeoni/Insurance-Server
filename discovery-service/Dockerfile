FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} discoveryServer.jar
ENTRYPOINT ["java","-jar","/discoveryServer.jar"]