# 📝 RENDER - CAMPOS EXACTOS A RELLENAR

**Viendo la pantalla que compartiste, aquí están los cambios exactos:**

---

## ✏️ CAMBIOS NECESARIOS

### 1. ❌ CAMBIAR: Name
**Actual:** `rolfing-web-project`  
**CAMBIAR A:** `rolfing-web`

```
Borra todo y escribe: rolfing-web
```

---

### 2. ❌ CAMBIAR: Language
**Actual:** `Node`  
**CAMBIAR A:** `Java`

```
Haz clic en el dropdown "Node"
Busca y selecciona: Java
```

---

### 3. ✅ DEJAR IGUAL: Branch
**Valor:** `main` ← Está correcto, no cambiar

---

### 4. ✅ DEJAR IGUAL: Region
**Valor:** `Oregon (US West)` ← Está bien, puedes cambiar si prefieres otra región

---

### 5. ✅ DEJAR IGUAL: Root Directory
**Valor:** (vacío) ← Está correcto, dejar en blanco

---

### 6. ❌ CAMBIAR: Build Command
**Actual:** `$ yarn`  
**CAMBIAR A:** `./gradlew clean build`

```
Borra "yarn" y escribe: ./gradlew clean build
```

---

### 7. ❌ CAMBIAR: Start Command
**Actual:** `$ yarn start`  
**CAMBIAR A:** `java -cp build/libs/rolfing.war com.rolfing.server.JettyServer`

```
Borra "yarn start" y escribe:
java -cp build/libs/rolfing.war com.rolfing.server.JettyServer
```

---

## 📋 RESUMEN VISUAL

```
ANTES (actual en pantalla):
┌─────────────────────────────────────────┐
│ Name:        rolfing-web-project        │ ← CAMBIAR
│ Language:    Node                       │ ← CAMBIAR
│ Branch:      main                       │ ← OK
│ Region:      Oregon (US West)           │ ← OK
│ Root Dir:    (vacío)                    │ ← OK
│ Build Cmd:   $ yarn                     │ ← CAMBIAR
│ Start Cmd:   $ yarn start               │ ← CAMBIAR
└─────────────────────────────────────────┘

DESPUÉS (como debe ser):
┌─────────────────────────────────────────┐
│ Name:        rolfing-web                │ ✅
│ Language:    Java                       │ ✅
│ Branch:      main                       │ ✅
│ Region:      Oregon (US West)           │ ✅
│ Root Dir:    (vacío)                    │ ✅
│ Build Cmd:   ./gradlew clean build      │ ✅
│ Start Cmd:   java -cp build/libs/...    │ ✅
└─────────────────────────────────────────┘
```

---

## 🔧 PASOS A SEGUIR

### Paso 1: Cambiar Name
1. Haz clic en el campo "Name"
2. Selecciona todo el texto
3. Borra y escribe: `rolfing-web`

### Paso 2: Cambiar Language
1. Haz clic en el dropdown "Node"
2. Desplázate si es necesario
3. Busca y selecciona: `Java`

### Paso 3: Cambiar Build Command
1. Haz clic en el campo "Build Command"
2. Selecciona todo (Ctrl+A)
3. Borra y escribe: `./gradlew clean build`

### Paso 4: Cambiar Start Command
1. Haz clic en el campo "Start Command"
2. Selecciona todo (Ctrl+A)
3. Borra y escribe: `java -cp build/libs/rolfing.war com.rolfing.server.JettyServer`

### Paso 5: Desplazarse hacia Abajo
Sigue bajando para ver si hay más campos (Environment Variables)

---

## 🔐 CAMPOS PARA COPIAR/PEGAR

Si prefieres copiar exacto:

**Name:**
```
rolfing-web
```

**Language:**
```
Java
```

**Build Command:**
```
./gradlew clean build
```

**Start Command:**
```
java -cp build/libs/rolfing.war com.rolfing.server.JettyServer
```

---

## 📍 PRÓXIMO: Environment Variables

Después de rellenar los campos arriba, desplázate hacia abajo.

Deberías ver una sección que dice:
```
Environment Variables (opcional)
```

o similar.

**Haz clic en "Add Environment Variable"** y rellena:

```
Key:   PORT
Value: 8080
```

---

## 🎯 CHECKLIST ANTES DE HACER CLIC EN "CREATE"

Antes de hacer clic en "Create Web Service", verifica:

- [ ] Name: `rolfing-web`
- [ ] Language: `Java` (no Node)
- [ ] Branch: `main`
- [ ] Region: Tu región preferida
- [ ] Root Directory: Vacío
- [ ] Build Command: `./gradlew clean build`
- [ ] Start Command: `java -cp build/libs/rolfing.war com.rolfing.server.JettyServer`
- [ ] Environment Variable PORT: `8080` (si existe la sección)

---

## 🚀 CUANDO TERMINES

1. **Verifica todo arriba** ✅
2. **Desplázate hacia el final** 👇
3. **Haz clic en "Create Web Service"** 🔵
4. **Espera 5-10 minutos** ⏳
5. **Tu app estará en vivo** 🎉

---

**¡Estos son EXACTAMENTE los cambios que necesitas hacer!**

*Creado: 13 de Abril de 2026*
