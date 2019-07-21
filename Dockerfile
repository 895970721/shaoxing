FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ARG PORT
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/app.jar"]
EXPOSE ${PORT}
