FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/mancala.jar
COPY ${JAR_FILE} mancala.jar
ENTRYPOINT ["java","-jar","/mancala.jar"]