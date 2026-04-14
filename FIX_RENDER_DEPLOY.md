# 🔧 Cómo Reparar el Deploy en Render

## Problema
Render no está subiendo la página y no hay errores visibles en la consola.

## Solución

### 1️⃣ Eliminar el servicio actual y crear uno nuevo

**En Render Dashboard:**

1. Ve a tu servicio `rolfing-web`
2. Haz click en **Settings** (engranaje abajo a la derecha)
3. Desplázate hasta **Danger Zone**
4. Click en **Delete Service**
5. Confirma

### 2️⃣ Crear un nuevo servicio desde cero

1. Haz click en **+ New +** → **Web Service**
2. Selecciona tu repositorio `rolfing-web-project` (GitHub)
3. **Configuración importante:**
   - **Name:** `rolfing-web`
   - **Runtime:** `Docker` ⚠️ **IMPORTANTE**
   - **Build Command:** (dejar vacío - Render lo detectará)
   - **Start Command:** (dejar vacío - Render lo detectará)
   - **Plan:** `Free`

### 3️⃣ Agregar variables de entorno

1. Después de crear el servicio, ve a **Environment**
2. Click en **Add Environment Variable** y agrega:

| Key | Value |
|---|---|
| `PORT` | `8080` |
| `MAIL_SMTP_HOST` | `smtp.gmail.com` |
| `MAIL_SMTP_PORT` | `587` |
| `MAIL_SMTP_USER` | `jmmartinezastroza@gmail.com` |
| `MAIL_SMTP_PASSWORD` | `reff bcpc rvio yoss` |

3. Click en **Save**

### 4️⃣ Esperar el deploy

- Render comenzará automáticamente a buildear y desplegar
- Ve a **Logs** para ver el progreso
- **Deberías ver:**
  ```
  ✅ JAR encontrado correctamente
  INICIANDO SERVIDOR ROLFING
  Puerto:  8080
  SERVIDOR LISTO
  ```

### 5️⃣ Verificar que funciona

1. Tu sitio debe estar en: `https://rolfing-web.onrender.com/rolfing`
2. Prueba el formulario de contacto
3. Revisa los logs para ver:
   ```
   CONFIGURACIÓN DE EMAIL CARGADA
   DEBUG: MAIL_SMTP_USER env: ✅ SET
   DEBUG: MAIL_SMTP_PASSWORD env: ✅ SET
   ✅ ÉXITO: Correo enviado
   ```

---

## Si aún falla después de esto:

### Revisar los logs en Render

En **Logs**, busca:
- `ERROR` o `Exception`
- `Connection refused`
- `Cannot find JAR`
- `No such file or directory`

### Posibles errores y soluciones:

**"ERROR: No se encontró rolfing-app.jar"**
- El build falló
- Revisa: ¿Hay errores en `build.gradle.kts`?
- Intenta compilar localmente: `./gradlew clean build`

**"java: command not found"**
- Docker image incorrecta
- Debe ser `eclipse-temurin:11-jre-alpine`

**"Port already in use"**
- Cambiar PORT a otro valor (ej: 3000)

**"Cannot load config.properties"**
- Verificar que está en `src/main/resources/`

---

## Alternativa: Usar Java Native Runtime (más rápido)

Si Docker sigue dando problemas, prueba:

1. Cambiar `render.yaml`:
```yaml
runtime: java11
buildCommand: ./gradlew clean build
startCommand: java -jar build/libs/rolfing-app.jar
```

2. Cambiar `build.gradle.kts`:
   - Comentar `tasks.war` (no necesitamos .war)
   - Mantener `fatJar` activo

Pero **Docker es más confiable**, así que primero intenta la opción anterior.
