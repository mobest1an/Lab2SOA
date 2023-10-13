FROM eclipse-temurin:17 as builder


WORKDIR /app

COPY . .
RUN ./gradlew --no-daemon services:flat-service:bootJar

FROM eclipse-temurin:17 as final
ARG VERSION=0.0.1-SNAPSHOT
ENV VERSION=${VERSION}

WORKDIR /app

COPY --from=builder /app/services/flat-service/build/libs/flat-service-${VERSION}.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
