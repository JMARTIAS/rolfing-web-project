# 🚀 Guía de Deployment Local - Rolfing Web Project

## 📋 Contenido de Este Documento

Esta guía contiene instrucciones para:
1. ✅ Ejecutar localmente con Jetty embebido
2. ✅ Configurar en Tomcat
3. ✅ Compilar para producción
4. ✅ Solucionar problemas comunes

---

## 1️⃣ Ejecución Local (Recomendado para desarrollo)

### Opción A: Usando el Servidor Embebido Jetty

**Ventajas:**
- ✅ No requiere instalación adicional
- ✅ Inicia rápidamente
- ✅ Ideal para desarrollo
- ✅ Fácil de debuggear

**Pasos:**

1. Abre PowerShell o CMD
2. Navega al proyecto:
   ```bash
   cd "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject"
   ```

3. Ejecuta el servidor:
   ```bash
   gradlew.bat runServer
   ```

4. Verás:
   ```
   ============================================================
   ✅ SERVIDOR INICIADO CORRECTAMENTE
   ============================================================
   🌐 Accede a: http://localhost:8080/rolfing
   ⏹️  Presiona Ctrl+C para detener el servidor
   ```

5. Abre tu navegador en:
   ```
   http://localhost:8080/rolfing
   ```

---

## 2️⃣ Deployment en Tomcat (Producción)

### Requisitos Previos

- [Tomcat 9+](https://tomcat.apache.org/download-90.cgi)
- Java JDK 11+
- La aplicación compilada (rolfing.war)

### Pasos de Instalación

#### Paso 1: Descargar e Instalar Tomcat

1. Descarga Tomcat desde: https://tomcat.apache.org/
2. Extrae en: `C:\Program Files\tomcat9` (o tu ubicación preferida)
3. Verifica que funciona correctamente

#### Paso 2: Compilar la Aplicación

En el directorio del proyecto:

```bash
cd "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject"
gradlew.bat clean build
```

Esto generará: `build/libs/rolfing.war`

#### Paso 3: Copiar WAR a Tomcat

1. Copia el archivo WAR:
   ```bash
   copy build\libs\rolfing.war "C:\Program Files\tomcat9\webapps\"
   ```

2. O simplemente arrastra el archivo a `tomcat9/webapps/`

#### Paso 4: Iniciar Tomcat

**Opción A: Desde Command Prompt**
```bash
cd C:\Program Files\tomcat9\bin
catalina.bat run
```

**Opción B: Usar el servicio de Windows**
```bash
# Instalar como servicio
C:\Program Files\tomcat9\bin\service.bat install

# Iniciar el servicio
net start Tomcat9
```

#### Paso 5: Acceder a la Aplicación

Espera 5-10 segundos para que Tomcat despliegue el WAR, luego abre:

```
http://localhost:8080/rolfing
```

---

## 3️⃣ Compilación para Producción

### Compilar WAR

```bash
cd "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject"
gradlew.bat clean build
```

**Resultado:**
```
✅ BUILD SUCCESSFUL
📦 Artifact: build/libs/rolfing.war
📊 Size: ~1.5 MB
```

### Verificar el WAR

```bash
# Listar contenido del WAR
jar tf build\libs\rolfing.war | head -20

# Verificar estructura
# Deberías ver:
# - index.html
# - servicios.html
# - css/
# - js/
# - WEB-INF/
```

### Optimización para Producción

Actualiza `build.gradle.kts` con estas mejoras:

```kotlin
// Agregar compresión
tasks.war {
    archiveFileName.set("rolfing.war")
    manifest {
        attributes(
            "Built-By" to System.getProperty("user.name"),
            "Build-Timestamp" to java.time.ZonedDateTime.now(),
            "Implementation-Version" to project.version
        )
    }
}
```

---

## 4️⃣ Configuración de Puertos

### Cambiar Puerto en Jetty (Desarrollo)

En `JettyServer.java`:
```java
private static final int PORT = 9090; // Cambiar de 8080 a 9090
```

Luego recompila:
```bash
gradlew.bat clean build
```

### Cambiar Puerto en Tomcat

Edita: `C:\Program Files\tomcat9\conf\server.xml`

Busca:
```xml
<Connector port="8080" protocol="HTTP/1.1"
```

Cambia a:
```xml
<Connector port="8090" protocol="HTTP/1.1"
```

Reinicia Tomcat.

---

## 5️⃣ Configuración HTTPS (SSL)

### Para Tomcat

1. Genera certificado:
   ```bash
   keytool -genkey -alias tomcat -keyalg RSA -keystore tomcat.keystore
   ```

2. Edita `server.xml`:
   ```xml
   <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
       maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
       keystoreFile="tomcat.keystore" keystorePass="password"
       clientAuth="false" sslProtocol="TLS" />
   ```

3. Accede por HTTPS:
   ```
   https://localhost:8443/rolfing
   ```

---

## 6️⃣ Monitoreo y Logs

### Logs de Jetty

Durante la ejecución, los logs aparecen en la consola:
```
21:06:19.760 [main] INFO org.eclipse.jetty.server.Server -- Started server
```

### Logs de Tomcat

Ubicación: `C:\Program Files\tomcat9\logs\`

Archivos importantes:
- `catalina.out` - Logs principales
- `catalina.YYYY-MM-DD.log` - Logs diarios
- `localhost.YYYY-MM-DD.log` - Logs de la aplicación

Ver logs en tiempo real:
```bash
# Windows PowerShell
Get-Content "C:\Program Files\tomcat9\logs\catalina.out" -Wait

# O en CMD
type "C:\Program Files\tomcat9\logs\catalina.out"
```

---

## 7️⃣ Troubleshooting

### Puerto ya está en uso

```bash
# Encontrar proceso usando puerto 8080
netstat -ano | findstr :8080

# Matar el proceso
taskkill /PID [PROCESS_ID] /F
```

### La aplicación no se despliega en Tomcat

```bash
# Verificar que el WAR está en webapps
dir "C:\Program Files\tomcat9\webapps\"

# Esperar a que se despliegue (puede tomar 10-30 segundos)
# Busca en los logs: "ContextLoader: startup completed"

# Si sigue sin funcionar, revisa los logs:
type "C:\Program Files\tomcat9\logs\catalina.out" | findstr rolfing
```

### Errores de permisos

```bash
# Ejecutar Command Prompt como Administrador
# Luego ejecutar nuevamente los comandos
```

### Contexto incorrecto

- **Jetty:** URL es `/rolfing` (configurado en JettyServer.java)
- **Tomcat:** URL es `/rolfing` (nombre del archivo WAR)

Si renombras a `myapp.war`, será `/myapp`

---

## 8️⃣ Verificación Post-Deployment

Después de desplegar, verifica:

```powershell
# Test 1: Servidor responde
$response = Invoke-WebRequest http://localhost:8080/rolfing -UseBasicParsing
Write-Host "Status: $($response.StatusCode)"

# Test 2: Contiene HTML válido
if ($response.Content -match "<html") { Write-Host "✅ HTML válido" }

# Test 3: Recursos cargan
$css = Invoke-WebRequest http://localhost:8080/rolfing/css/styles.css -UseBasicParsing
Write-Host "CSS Status: $($css.StatusCode)"
```

---

## 9️⃣ Performance Tuning

### Jetty
En `JettyServer.java`, agregar:

```java
// Aumentar threads
QueuedThreadPool threadPool = new QueuedThreadPool();
threadPool.setMaxThreads(200);
threadPool.setMinThreads(50);
server.setThreadPool(threadPool);
```

### Tomcat
En `server.xml`:

```xml
<Connector port="8080" protocol="HTTP/1.1"
    maxThreads="200"
    minSpareThreads="50"
    maxConnections="1000"
    connectionTimeout="20000"/>
```

---

## 🔟 Respaldos y Restauración

### Hacer respaldo

```bash
# Backup del WAR
copy build\libs\rolfing.war rolfing.war.backup

# Backup de la carpeta del proyecto
robocopy "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject" "C:\Backups\RolfingWebProject" /E /Z /W:1 /R:1
```

### Restaurar

```bash
# Restaurar WAR
copy rolfing.war.backup build\libs\rolfing.war

# Recompilar si es necesario
gradlew.bat clean build
```

---

## 📊 Información de Deployment Actual

| Aspecto | Valor |
|---------|-------|
| **Puerto** | 8080 |
| **Host** | 0.0.0.0 |
| **Contexto** | /rolfing |
| **Servidor** | Jetty 11.0.15 |
| **Java** | 11+ |
| **Tamaño WAR** | ~1.5 MB |
| **Ruta Proyecto** | C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject |

---

## 📞 Contacto y Soporte

Si tienes problemas:

1. **Revisar logs:**
   - Jetty: Console output
   - Tomcat: `logs/catalina.out`

2. **Verificar puerto:**
   ```bash
   netstat -ano | findstr :8080
   ```

3. **Reiniciar servidor:**
   - Presiona Ctrl+C en Jetty
   - O reinicia Tomcat

4. **Limpiar caché:**
   - Borra `work/` en Tomcat
   - Ctrl+Shift+Del en navegador

---

**¡Listo! Tu aplicación está completamente deployada. 🎉**
