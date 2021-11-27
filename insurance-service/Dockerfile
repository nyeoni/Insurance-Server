FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} insuranceServer.jar
ENTRYPOINT ["java","-jar","/insuranceServer.jar"]