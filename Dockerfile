FROM openjdk:17-jdk-alpine3.13

# Install curl for healthcheck
RUN apk --no-cache add curl

COPY target/myfinance*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
