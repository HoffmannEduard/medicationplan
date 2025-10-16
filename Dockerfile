# 1 Runtime
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Das Artefakt aus GitHub Actions Job
COPY *.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
