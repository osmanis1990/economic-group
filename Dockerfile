FROM alpine/java:21

WORKDIR /app

COPY ./target/java-1.0-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "java-1.0-SNAPSHOT.jar"]