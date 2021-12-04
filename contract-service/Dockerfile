FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} contractServer.jar
ENTRYPOINT ["java","-jar","/contractServer.jar"]