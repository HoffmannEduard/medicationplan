# 1 Basis-Image mit Java 21
FROM eclipse-temurin:21-jdk-alpine

# 2 Arbeitsverzeichnis im Container
WORKDIR /app

# 3 Spring Boot Jar ins Image kopieren
COPY build/libs/*.jar app.jar

# 4 Port 8080 exportieren
EXPOSE 8080

# 5 Startbefehl für die App
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
