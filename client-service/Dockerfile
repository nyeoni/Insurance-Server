FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} clientServer.jar
ENTRYPOINT ["java","-jar","/clientServer.jar"]