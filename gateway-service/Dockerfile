FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} gatewayServer.jar
ENTRYPOINT ["java","-jar","/gatewayServer.jar"]