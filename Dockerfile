FROM openjdk:17-jdk-alpine3.13

COPY target/myfinance*.jar /app.jar

CMD sh -c "java -jar /app.jar & sleep 50 && kill $! && tail -f /dev/null"
