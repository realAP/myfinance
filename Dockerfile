FROM openjdk:17-jdk-alpine3.13
COPY target/myfinance*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]