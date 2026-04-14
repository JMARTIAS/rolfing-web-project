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

# Verificar que se creó el JAR
RUN ls -lah /app/build/libs/

# Etapa 2: Runtime - Usar imagen disponible de Eclipse Temurin (OpenJDK)
FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

# Instalar bash para debugging (opcional)
RUN apk add --no-cache bash curl

# Copiar el Fat JAR (rolfing-app.jar con todas las dependencias)
COPY --from=builder /app/build/libs/rolfing-app.jar .

# Copiar los archivos web (necesarios para Jetty)
COPY --from=builder /app/src/main/webapp ./src/main/webapp

# Copiar el archivo de configuración
COPY --from=builder /app/src/main/resources ./src/main/resources

# Verificar que existe el JAR ejecutable
RUN ls -lah *.jar && echo "✅ JAR encontrado correctamente" || (echo "❌ ERROR: No se encontró rolfing-app.jar" && exit 1)

# Puerto
EXPOSE 8080

# Comando para ejecutar - Ejecutar directamente el Fat JAR
CMD ["java", "-jar", "rolfing-app.jar"]
