# Guía: Compilar y Ejecutar el Proyecto

## 📋 Requisitos Previos

- **Java JDK 11 o superior** instalado
- **Gradle 7.0 o superior** (o usar el wrapper incluido)
- **Conexión a Internet** (para descargar dependencias)

## 🚀 Pasos para Compilar y Ejecutar

### 1. **Abre una terminal/consola en la carpeta del proyecto**

```bash
# Windows (PowerShell)
cd C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject

# macOS/Linux
cd ~/AndroidStudioProjects/RolfingWebProject
```

### 2. **Compila el proyecto**

#### Opción A: Usar Gradle Wrapper (Recomendado)

```bash
# Windows
gradlew.bat build

# macOS/Linux
./gradlew build
```

#### Opción B: Usar Gradle instalado

```bash
gradle build
```

**Esto descargará todas las dependencias y compilará el proyecto.**

### 3. **Ejecuta el servidor**

#### Opción A: Usando Gradle (Recomendado)

```bash
# Windows
gradlew.bat runServer

# macOS/Linux
./gradlew runServer
```

#### Opción B: Usando el JAR compilado

```bash
# Windows
java -jar build/libs/rolfing-app.jar

# macOS/Linux
java -jar build/libs/rolfing-app.jar
```

### 4. **Accede a la aplicación**

Abre tu navegador y ve a:
```
http://localhost:8080/rolfing
```

## 📧 Configurar Correos Electrónicos

Una vez que la aplicación esté ejecutándose:

1. Abre `config.properties` en la raíz del proyecto
2. Configura tu servidor SMTP (ver `EMAIL_SETUP.md`)
3. Cambia `email.enabled=true`
4. Reinicia el servidor

## 🛑 Detener el Servidor

Presiona `Ctrl+C` en la terminal donde se está ejecutando el servidor.

## 📦 Archivos Generados

Después de compilar, se crean:
- `build/` - Carpeta con los archivos compilados
- `build/libs/rolfing-app.jar` - JAR ejecutable
- `build/libs/rolfing.war` - WAR para despliegue en aplicaciones servidores

## ✅ Verificar que Todo Funciona

1. **Frontend cargado**: Deberías ver la página de inicio de Rolfing
2. **Formulario funciona**: Completa el formulario de contacto
3. **Respuesta del servidor**: Deberías ver un mensaje de éxito
4. **Correos enviados**: Revisa tu bandeja de entrada (si configuraste SMTP)

## 🐛 Solución de Problemas

### "gradlew command not found" o "No se reconoce el comando"

En Windows, intenta:
```bash
.\gradlew.bat build
```

En macOS/Linux, asegúrate que el archivo sea ejecutable:
```bash
chmod +x gradlew
./gradlew build
```

### "Java no se encuentra"

Instala Java JDK 11 o superior desde:
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- [OpenJDK](https://adoptopenjdk.net/)

### El servidor no inicia en el puerto 8080

- Verifica que el puerto 8080 esté disponible
- Cambia el puerto en `config.properties`: `web.port=8081`

### "No se puede conectar a localhost:8080"

- Asegúrate de que el servidor está ejecutándose
- Verifica los logs para errores
- Intenta acceder a `http://127.0.0.1:8080/rolfing`

## 📝 Notas Importantes

- La compilación inicial puede tardar varios minutos (descargando dependencias)
- Las compilaciones posteriores serán más rápidas
- El archivo `config.properties` debe estar en `src/main/resources/` para que funcione correctamente
- Los logs se mostrarán en la consola de la terminal

## 🎯 Siguiente Paso

Una vez que la aplicación esté ejecutándose correctamente, sigue la guía en `EMAIL_SETUP.md` para configurar el envío de correos.

---

¿Problemas? Revisa los logs en la consola para más detalles sobre qué salió mal.
