FROM amazoncorretto:25-al2023-headless

# curl ist im Basis-Image bereits enthalten und wird vom Healthcheck
# in compose.yaml benoetigt - eine Installation ist nicht noetig.

COPY --chown=1001:0 target/myfinance*.jar /app.jar

# Nicht als root laufen. Eine numerische UID genuegt, das Image bringt
# kein useradd mit und soll dafuer auch nicht aufgeblaeht werden.
USER 1001

CMD ["java", "-jar", "/app.jar"]
