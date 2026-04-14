# ⚠️ RENDER - CONFIGURACIÓN CORRECTA

**IMPORTANTE:** La pantalla que viste es para "Static Site", pero tu app necesita "Web Service"

---

## 🚫 LO QUE NO DEBES HACER

La pantalla que compartiste es para **"Static Site"** (solo HTML/CSS/JS estático):
```
Name: rolfing-web-project
Branch: main
Root Directory: (dejar en blanco)
Build Command: (campo)
Publish Directory: build (o similar)
```

�� **ESTO NO FUNCIONA PARA TU APP JAVA**

---

## ✅ LO QUE DEBES HACER

### Paso 1: Cancelar / Volver Atrás
Si ya empezaste, haz clic en **"Back"** o cancela este formulario.

### Paso 2: Crear Web Service (NO Static Site)

1. En Render Dashboard, haz clic en **"+ New"** (esquina superior derecha)
2. Selecciona **"Web Service"** (NO "Static Site")
3. Selecciona **"Build and deploy from a Git repository"**

### Paso 3: Conectar Repositorio
1. Autoriza GitHub si es necesario
2. Selecciona: **`rolfing-web-project`**

### Paso 4: Rellenar Configuración

En la nueva pantalla que aparecerá, rellena **EXACTAMENTE** así:

```
┌────────────────────────────────────────────────┐
│ CONFIGURACIÓN DEL WEB SERVICE                  │
├────────────────────────────────────────────────┤
│                                                │
│ Name:                                          │
│ ╔══════════════════════════════════════════╗  │
│ ║ rolfing-web                              ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ Environment: (dropdown)                        │
│ ╔══════════════════════════════════════════╗  │
│ ║ Java (o auto-detect)                    ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ Region: (dropdown, elige tu región)            │
│ ╔══════════════════════════════════════════╗  │
│ ║ Frankfurt (EU)   [o tu región]           ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ Plan: (dropdown)                               │
│ ╔══════════════════════════════════════════╗  │
│ ║ Free                                     ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ Build Command:                                 │
│ ╔══════════════════════════════════════════╗  │
│ ║ ./gradlew clean build                    ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ Start Command:                                 │
│ ╔══════════════════════════════════════════╗  │
│ ║ java -cp build/libs/rolfing.war          ║  │
│ ║ com.rolfing.server.JettyServer           ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
└────────────────────────────────────────────────┘
```

### Paso 5: Agregar Variable de Entorno

Desplázate hacia abajo y busca **"Environment"**

Haz clic en **"Add Environment Variable"**

Rellena:
```
┌────────────────────────────────────────────────┐
│ Key:                                           │
│ ╔══════════════════════════════════════════╗  │
│ ║ PORT                                     ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ Value:                                         │
│ ╔══════════════════════════════════════════╗  │
│ ║ 8080                                     ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ [Add] button                                   │
└────────────────────────────────────────────────┘
```

### Paso 6: Desplegar

1. Desplázate al final de la página
2. Haz clic en **"Create Web Service"** (botón azul grande)
3. **ESPERA** 5-10 minutos

---

## 📋 CAMPOS COMPLETOS PARA COPIAR/PEGAR

Si necesitas valores exactos:

**Name:**
```
rolfing-web
```

**Environment:**
```
Java
```

**Region:**
```
Frankfurt (EU) [o la más cercana a ti]
```

**Plan:**
```
Free
```

**Build Command:**
```
./gradlew clean build
```

**Start Command:**
```
java -cp build/libs/rolfing.war com.rolfing.server.JettyServer
```

**Environment Variable - Key:**
```
PORT
```

**Environment Variable - Value:**
```
8080
```

---

## ✅ VERIFICACIÓN

Después de hacer clic en "Create Web Service":

1. **Verás logs de compilación:**
   ```
   Cloning repository...
   Building with gradle...
   Starting server...
   ```

2. **Busca este mensaje:**
   ```
   ✅ Service started successfully
   ```

3. **Verás una URL:**
   ```
   https://rolfing-web.onrender.com
   ```

4. **Haz clic en la URL** → ¡Tu app carga! 🎉

---

## 🔴 SI VES ERRORES

### Error: "Build failed"
- Revisa los logs
- Verifica que el Build Command es correcto
- Intenta "Redeploy latest commit"

### Error: "Service failed to start"
- Verifica el Start Command
- Revisa que PORT esté configurado como 8080
- Los logs dirán qué está mal

### Error: "Cannot connect"
- Espera más minutos (a veces tarda 10-15)
- Intenta recargar la página

---

## 💡 DIFERENCIA ENTRE STATIC SITE Y WEB SERVICE

| Característica | Static Site | Web Service |
|---|---|---|
| **Para qué** | HTML/CSS/JS puro | Apps con servidor |
| **Tu app** | ❌ NO | ✅ SÍ |
| **Java** | ❌ No soporta | ✅ Soporta |
| **Procfile** | ❌ No necesita | ✅ Lo usa |
| **Build/Start Commands** | ❌ No | ✅ Sí |

**TU APP NECESITA: Web Service** ✅

---

## 🎯 RESUMEN RÁPIDO

1. ✅ Ve a Render.com
2. ✅ Haz clic en "+ New"
3. ✅ Selecciona **"Web Service"** (NO Static Site)
4. ✅ Selecciona tu repositorio
5. ✅ Rellena los 6 campos
6. ✅ Haz clic en "Create Web Service"
7. ✅ Espera 10 minutos
8. ✅ Accede a tu URL 🎉

---

## 📞 SI NECESITAS AYUDA

Si algo no está claro:
- Los campos necesarios están arriba
- Consulta RENDER_DEPLOYMENT_FINAL.md en tu repo
- Revisa los logs en Render (dirán qué está mal)

---

**¡Ahora sí va a funcionar! Web Service es lo correcto para tu app Java.** 🚀

*Creado: 13 de Abril de 2026*
