# Guía de Deployment - Rolfing Web Project

## 📋 Requisitos Previos

- Java JDK 11 o superior instalado
- Gradle 6.0+ (incluido en el proyecto)
- Acceso a un servidor web (Tomcat, Jetty, etc.)
- Navegador moderno para pruebas

## 🏗️ Build Local

### 1. Compilar el Proyecto

```bash
# En Windows
gradlew.bat clean build

# En Linux/Mac
./gradlew clean build
```

### 2. Ejecutar en Servidor Integrado

```bash
# Inicia la aplicación en http://localhost:8080/rolfing
./gradlew jettyRun
```

## 🚀 Deployment en Tomcat

### 1. Preparar el Servidor

```bash
# Descargar Tomcat
# https://tomcat.apache.org/download-90.cgi

# Extraer en carpeta deseada
cd /path/to/tomcat
```

### 2. Compilar WAR

```bash
./gradlew build
# El archivo WAR estará en: build/libs/rolfing.war
```

### 3. Deployar

```bash
# Opción A: Copiar WAR a webapps
cp build/libs/rolfing.war /path/to/tomcat/webapps/

# Opción B: Usar Tomcat Manager GUI
# http://localhost:8080/manager/
# (requiere usuario configurado)
```

### 4. Iniciar Tomcat

```bash
# En Windows
catalina.bat run

# En Linux/Mac
./catalina.sh run
```

### 5. Verificar Deployment

```
http://localhost:8080/rolfing/
```

## 🔒 Configuración Segura

### 1. Configurar HTTPS

```bash
# Generar certificado auto-firmado (desarrollo)
keytool -genkey -alias tomcat -keyalg RSA -keystore keystore.jks

# Editar conf/server.xml
# Descomentar la línea de puerto 8443
```

### 2. Cambiar Puerto Predeterminado

Editar `conf/server.xml`:
```xml
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```

### 3. Configurar Variables de Entorno

```bash
# Linux/Mac
export CATALINA_OPTS="-Xmx512m -Xms256m"

# Windows
set CATALINA_OPTS=-Xmx512m -Xms256m
```

## 📦 Deployment en Producción

### 1. Usar Reverse Proxy (Nginx)

```nginx
server {
    listen 80;
    server_name rolfing.com;
    
    location / {
        proxy_pass http://localhost:8080/rolfing;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

### 2. Habilitar HTTPS con Let's Encrypt

```bash
# Instalar Certbot
sudo apt install certbot python3-certbot-nginx

# Obtener certificado
sudo certbot --nginx -d rolfing.com
```

### 3. Optimizar Performance

```bash
# En server.xml, configurar thread pool
<Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
    maxThreads="200" minSpareThreads="50"/>

<Connector port="8080" protocol="HTTP/1.1"
           executor="tomcatThreadPool"
           connectionTimeout="20000"
           redirectPort="8443" />
```

## 🗄️ Base de Datos (Futura Integración)

### 1. Crear Base de Datos

```sql
-- MySQL ejemplo
CREATE DATABASE rolfing_db;
CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    asunto VARCHAR(100),
    mensaje TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 2. Configurar Datasource

Editar `WEB-INF/context.xml`:
```xml
<Context>
    <Resource name="jdbc/rolfingDB"
              auth="Container"
              type="javax.sql.DataSource"
              driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/rolfing_db"
              username="root"
              password="password"
              maxActive="100"
              maxIdle="30"
              maxWait="10000"/>
</Context>
```

## 📊 Monitoreo

### 1. Ver Logs

```bash
# Tomcat logs
tail -f logs/catalina.out

# Aplicación logs
tail -f logs/application.log
```

### 2. Monitorear Procesos

```bash
# Ver procesos Java
jps -l

# Monitorear estado
jconsole
```

## 🔧 Mantenimiento

### 1. Actualizar Aplicación

```bash
# Detener Tomcat
./catalina.sh stop

# Sacar backup
cp webapps/rolfing.war backups/rolfing-$(date +%Y%m%d).war

# Compilar nueva versión
./gradlew build

# Copiar nuevo WAR
cp build/libs/rolfing.war webapps/

# Iniciar Tomcat
./catalina.sh start
```

### 2. Limpiar Datos Temporales

```bash
# Limpiar cache
rm -rf work/Catalina/localhost/rolfing/

# Limpiar logs viejos (>30 días)
find logs -name "*.log" -mtime +30 -delete
```

## ⚠️ Troubleshooting

### Puerto en Uso

```bash
# En Windows
netstat -ano | findstr :8080

# En Linux/Mac
lsof -i :8080

# Matar proceso
kill -9 <PID>
```

### Aplicación No Carga

1. Verificar logs: `tail -f logs/catalina.out`
2. Verificar puerto: `netstat -ano | findstr :8080`
3. Verificar permisos: `chmod 755 rolfing.war`
4. Verificar Java version: `java -version`

### Memoria Insuficiente

```bash
# Aumentar memoria disponible
export CATALINA_OPTS="-Xmx1024m -Xms512m"
./catalina.sh start
```

## 📱 Testing de Deployment

```bash
# Probar en local
curl http://localhost:8080/rolfing/

# Comprobar status
curl -I http://localhost:8080/rolfing/

# Probar formulario
curl -X POST http://localhost:8080/rolfing/api/contact \
  -d "nombre=Test&email=test@example.com&mensaje=Test"
```

## 🎯 Checklist Pre-Producción

- [ ] Código compilado sin errores
- [ ] Tests ejecutados
- [ ] HTTPS habilitado
- [ ] Base de datos configurada
- [ ] Variables de entorno seteadas
- [ ] Backups configurados
- [ ] Monitoreo activado
- [ ] Logs configurados
- [ ] Seguridad: firewall, SSL, etc.
- [ ] Performance: caché, compresión, CDN
- [ ] Email notifications configurado
- [ ] Disaster recovery plan

## 📞 Soporte Adicional

Para soporte técnico, consulta la documentación oficial:
- Tomcat: https://tomcat.apache.org/
- Gradle: https://gradle.org/
- Java: https://www.oracle.com/java/

---

**Última actualización:** 2026-04-13
**Versión:** 1.0.0
