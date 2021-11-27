FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} configServer.jar
ENTRYPOINT ["java","-jar","/configServer.jar"]