# Multi-stage build para Rolfing Web Project
# Etapa 1: Compilar la aplicación
FROM gradle:8.0-jdk11 AS builder

WORKDIR /app

# Copiar archivos necesarios
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle/ ./gradle/
COPY gradlew .
COPY gradlew.bat .
COPY src/ ./src/

# Dar permisos de ejecución a gradlew
RUN chmod +x ./gradlew

# Compilar
RUN ./gradlew clean build -x test

# Etapa 2: Runtime - Usar imagen disponible de Eclipse Temurin (OpenJDK)
FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

# Copiar el WAR compilado desde la etapa anterior
COPY --from=builder /app/build/libs/rolfing.war .

# Copiar los archivos web
COPY --from=builder /app/src/main/webapp ./src/main/webapp

# Puerto
EXPOSE 8080

# Comando para ejecutar
CMD ["java", "-cp", "rolfing.war", "com.rolfing.server.JettyServer"]
