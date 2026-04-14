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

# Compilar y crear fat JAR
RUN ./gradlew clean build -x test

# Etapa 2: Runtime - Usar imagen disponible de Eclipse Temurin (OpenJDK)
FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

# Instalar bash para debugging (opcional)
RUN apk add --no-cache bash curl

# Copiar el Fat JAR (rolfing-app.jar con todas las dependencias)
COPY --from=builder /app/build/libs/rolfing-app.jar .

# Copiar los archivos web (por si se necesitan)
COPY --from=builder /app/src/main/webapp ./src/main/webapp

# Verificar que existe el JAR ejecutable
RUN ls -lah *.jar || echo "ERROR: No se encontró el JAR ejecutable"

# Puerto
EXPOSE 8080

# Comando para ejecutar - Ejecutar directamente el Fat JAR
CMD ["java", "-jar", "rolfing-app.jar"]
