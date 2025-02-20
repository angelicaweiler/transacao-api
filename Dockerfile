FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/transacao-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]