FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} userServer.jar
ENTRYPOINT ["java","-jar","/contractServer.jar"]