# Multi-stage build para Rolfing Web Project
# Etapa 1: Compilar la aplicación
FROM gradle:7.6-jdk11 AS builder

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

# Instalar bash para debugging (opcional)
RUN apk add --no-cache bash curl

# Copiar toda la carpeta de build (incluyendo libs)
COPY --from=builder /app/build ./build

# Copiar los archivos web
COPY --from=builder /app/src/main/webapp ./src/main/webapp

# Verificar que existe el archivo WAR
RUN ls -lah build/libs/ || echo "ERROR: No se encontró build/libs"

# Puerto
EXPOSE 8080

# Comando para ejecutar - Usar la carpeta build completa
CMD ["java", "-cp", "build/libs/rolfing.war:build/libs/*", "com.rolfing.server.JettyServer"]
