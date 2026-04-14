# 📝 RENDER - SI NO VES JAVA EN EL DROPDOWN

**No te preocupes. Esto es normal.** Render detectará automáticamente que es Java.

---

## ✅ QUÉ HACER

### Opción 1: Usar "Docker" (RECOMENDADO)

Si ves esta lista:
```
- Node (seleccionado)
- Docker
- Elixir
- Go
- Python 3
- Ruby
- Rust
```

**Selecciona "Docker"**

Render usará Docker para ejecutar tu app Java, lo cual funciona perfectamente.

---

### Opción 2: Dejar en "Node" (también funciona)

Aunque parezca raro, Render detectará automáticamente el tipo de proyecto por:
- El archivo `Procfile` (que tienes)
- El archivo `build.gradle.kts` (que tienes)
- Los archivos `.java` en el repo

Así que incluso si dejas "Node", Render sabrá que es Java y lo manejará correctamente.

---

## 🎯 RECOMENDACIÓN

**Usa "Docker"** porque:
1. ✅ Explícitamente dice que es un contenedor
2. ✅ Render lo maneará como una aplicación contenizada
3. ✅ Es más seguro y predecible
4. ✅ Funciona perfectamente para Java

---

## 📋 PASOS FINALES

1. **Haz clic en el dropdown Language** (donde dice "Node")
2. **Selecciona "Docker"**
3. **Rellena los otros campos:**
   - Name: `rolfing-web`
   - Branch: `main` (ya está)
   - Build Command: `./gradlew clean build`
   - Start Command: `java -cp build/libs/rolfing.war com.rolfing.server.JettyServer`
4. **Desplázate hacia abajo**
5. **Busca "Environment Variables"**
6. **Agrega:**
   - Key: `PORT`
   - Value: `8080`
7. **Haz clic en "Create Web Service"**

---

## ✨ IMPORTANTE

**Render NO necesita que selecciones "Java" específicamente.**

Porque:
- ✅ Tu proyecto tiene `Procfile` (le dice cómo ejecutarse)
- ✅ Tu proyecto tiene `build.gradle.kts` (indica que es Gradle/Java)
- ✅ Render es lo suficientemente inteligente para detectarlo

**Selecciona "Docker"** y Render hará el resto automáticamente.

---

## 🚀 RESUMEN

```
Language: Docker (o Node también funciona)
Build Command: ./gradlew clean build
Start Command: java -cp build/libs/rolfing.war com.rolfing.server.JettyServer
PORT: 8080
```

**Haz clic en "Create Web Service" y espera 10 minutos.**

Tu app estará en línea en: `https://rolfing-web.onrender.com`

---

*Creado: 13 de Abril de 2026*
