FROM gradle:8.5-jdk21 AS build
WORKDIR /build
COPY . .
RUN gradle bootJar --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /build/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]