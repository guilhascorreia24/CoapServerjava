FROM openjdk:17-alpine
VOLUME /coap_server
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 5683
