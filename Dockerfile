FROM openjdk:17-jdk-alpine3.13

#install curl for healthcheck
RUN apk update && apk --no-cache add curl

COPY target/myfinance*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
