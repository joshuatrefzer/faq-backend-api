FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/faq-backend-api-0.0.1.jar app.jar
ENTRYPOINT ["java" , "-jar" , "/app.jar"]