# Etapa de compilación
FROM gradle:8.4-jdk17 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . .

# Usa Gradle wrapper si existe, si no gradle instalado
RUN ./gradlew build --no-daemon --stacktrace

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el .jar compilado
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
