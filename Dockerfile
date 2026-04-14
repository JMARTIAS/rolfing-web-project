# Multi-stage build para Rolfing Web Project
# Etapa 1: Compilar la aplicación
FROM gradle:8.0-jdk11 as builder

WORKDIR /app

# Copiar archivos necesarios
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle/ ./gradle/
COPY gradlew .
COPY gradlew.bat .
COPY src/ ./src/

# Compilar
RUN ./gradlew clean build -x test

# Etapa 2: Runtime
FROM openjdk:11-jre-slim

WORKDIR /app

# Copiar el WAR compilado desde la etapa anterior
COPY --from=builder /app/build/libs/rolfing.war .

# Copiar los archivos web (por si acaso)
COPY --from=builder /app/src/main/webapp ./src/main/webapp

# Copiar la clase del servidor
COPY --from=builder /app/build/classes/java/main ./build/classes/java/main

# Copiar las dependencias
COPY --from=builder /app/build/resources/main ./build/resources/main

# Puerto
EXPOSE 8080

# Comando para ejecutar
CMD ["java", "-cp", "rolfing.war", "com.rolfing.server.JettyServer"]
