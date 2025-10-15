# 1 Build
FROM gradle:8.10-jdk21-alpine AS build

WORKDIR /app

COPY . .

# Projekt bauen
RUN gradle clean build --no-daemon

# 2 Runtime
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]