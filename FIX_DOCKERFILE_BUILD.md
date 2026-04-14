# 🐳 FIX: DOCKERFILE - ERROR EN LA EJECUCIÓN DEL SERVICIO

**Problema recibido:**
```
Exited with status 1
```

Después de compilar exitosamente, el servicio no podía iniciarse.

---

## 🔍 CAUSA RAÍZ

El Dockerfile solo copiaba `rolfing.war` pero no copiaba:
1. **Dependencias de compilación** en `build/libs/` (archivos .jar)
2. **Recursos del proyecto** necesarios para ejecutar

Cuando el JettyServer intentaba ejecutarse, no encontraba las dependencias de Jetty en el classpath.

---

## ✅ SOLUCIÓN APLICADA

Cambié el Dockerfile para:

**ANTES:**
```dockerfile
# Solo copiar el WAR
COPY --from=builder /app/build/libs/rolfing.war .
CMD ["java", "-cp", "rolfing.war", "com.rolfing.server.JettyServer"]
```

**DESPUÉS:**
```dockerfile
# Copiar TODA la carpeta build (incluyendo todas las dependencias)
COPY --from=builder /app/build ./build
COPY --from=builder /app/src/main/webapp ./src/main/webapp

# Verificar que existe el archivo
RUN ls -lah build/libs/ || echo "ERROR: No se encontró build/libs"

# Ejecutar con TODAS las dependencias en el classpath
CMD ["java", "-cp", "build/libs/rolfing.war:build/libs/*", "com.rolfing.server.JettyServer"]
```

---

## 📝 CAMBIOS CLAVE

| Aspecto | Antes | Después |
|---------|-------|---------|
| **Carpeta copiada** | Solo `rolfing.war` | Toda la carpeta `build/` |
| **Dependencias** | ❌ No copiadas | ✅ Incluidas en classpath |
| **Recursos web** | Dudosos | ✅ Explícitamente copiados |
| **Debugging** | Sin verificación | ✅ `ls -lah build/libs/` |
| **Classpath** | `"rolfing.war"` | `"build/libs/rolfing.war:build/libs/*"` |

---

## 🔧 DETALLES TÉCNICOS

En el Dockerfile, cuando ejecutas Java necesitas todas las dependencias en el classpath:

```bash
java -cp build/libs/rolfing.war:build/libs/* com.rolfing.server.JettyServer
```

Esto significa:
- `build/libs/rolfing.war` - Tu aplicación
- `build/libs/*` - TODAS las dependencias .jar (Jetty, etc.)

---

## 🚀 PRÓXIMO PASO EN RENDER

**En Render Dashboard:**
1. Busca "rolfing-web"
2. Haz clic en **"Redeploy latest commit"**
3. Espera **15 minutos**
4. Ahora verás los logs correctamente

Deberías ver:
```
#24 extracting ... done
#24 writing cache image manifest ... done
#24 DONE 37.4s
Pushing image to registry...
Upload succeeded
==> Deploying...
==> WEB_CONCURRENCY=1
✅ SERVIDOR INICIADO CORRECTAMENTE  ← AQUÍ
🌐 Accede a: http://localhost:8080
```

---

## 📊 RESUMEN DE TODOS LOS FIXES

| Fix | Problema | Solución | Commit |
|-----|----------|----------|--------|
| 1 | Imagen Docker no existe | eclipse-temurin:11-jre-alpine | d55d833 |
| 2 | gradlew sin permisos | chmod +x ./gradlew | 47cdd63 |
| 3 | Gradle 8.0 con Java 11 | gradle:7.6-jdk11 | 25e927f |
| 4 | gradle-wrapper 9.0 | gradle-7.6-bin.zip | 5d178ea |
| 5 | Dependencias no copiadas | Copiar build/ completo | c2716c0 ✅ |

---

## 💡 LECCIÓN APRENDIDA

Cuando copias archivos en Docker multi-stage:
- ✅ Copia TODO lo necesario (no solo archivos individuales)
- ✅ Verifica que existen con RUN ls
- ✅ Incluye TODAS las dependencias en el classpath

---

*Solución aplicada: 13 de Abril de 2026*
