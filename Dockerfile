FROM alpine/java:21-jdk AS builder

WORKDIR /app

COPY build.gradle .
COPY gradlew .
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew build

FROM eclipse-temurin:21-jre-alpine AS runtime
COPY --from=builder app/build/libs/*.jar app/*.jar

CMD ["java", "-jar", "/app/*.jar"]