# 🚀 PASOS PARA SUBIR TU APP A RENDER (Opción A)

**Estado:** Tu repositorio Git local está 100% listo. Solo necesitamos hacer 3 pasos simples.

---

## 📋 Requisitos (SOLO ESTOS 3)

- [ ] Cuenta GitHub (gratuita) - [Crear aquí](https://github.com/signup)
- [ ] Cuenta Render (gratuita) - [Crear aquí](https://render.com)
- [ ] Tu email

**Tiempo total:** 15 minutos

---

## 🔑 PASO 1: Crear Repositorio en GitHub

### 1.1 Crear la Cuenta
1. Abre [GitHub.com](https://github.com)
2. Haz clic en "Sign up"
3. Completa los datos:
   - Email
   - Contraseña
   - Username (ej: `tu_nombre_usuario`)
4. Verifica tu email

### 1.2 Crear un Repositorio Nuevo
1. Inicia sesión en GitHub
2. Haz clic en el `+` en la esquina superior derecha
3. Selecciona "New repository"
4. Rellena con:
   - **Repository name:** `rolfing-web-project`
   - **Description:** "Sitio web de Rolfing - Terapia Muscular Integral"
   - **Public:** ✅ (debe ser público para que Render acceda)
   - **Initialize:** Sin inicializar nada (ya tenemos código local)
5. Haz clic en "Create repository"

### 1.3 Resultado
GitHub te mostrará instrucciones como estas. **CÓPIALAS Y PÉGALAS EN TU TERMINAL:**

```bash
git remote add origin https://github.com/[TU_USERNAME]/rolfing-web-project.git
git branch -M main
git push -u origin main
```

---

## 💾 PASO 2: Subir Código a GitHub

### 2.1 En tu PowerShell/CMD

**Navega a tu proyecto:**
```bash
cd "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject"
```

**Ejecuta estos comandos (que GitHub te proporcionó):**

```bash
git remote add origin https://github.com/[TU_USERNAME]/rolfing-web-project.git
git branch -M main
git push -u origin main
```

**Reemplaza `[TU_USERNAME]` con tu usuario de GitHub.**

### 2.2 Esperar a que Termine

Verás algo como:
```
Enumerating objects: 35, done.
...
To https://github.com/[TU_USERNAME]/rolfing-web-project.git
 * [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

✅ Cuando veas "Branch 'main' set up to track..." significa que ¡éxito!

### 2.3 Verificar en GitHub

1. Abre GitHub.com
2. Inicia sesión
3. Ve a tu nuevo repositorio: `https://github.com/[TU_USERNAME]/rolfing-web-project`
4. Deberías ver todos los archivos de tu proyecto

---

## 🌐 PASO 3: Desplegar en Render

### 3.1 Crear Cuenta en Render
1. Abre [Render.com](https://render.com)
2. Haz clic en "Get Started"
3. Selecciona "Sign up with GitHub"
4. Autoriza Render para acceder a tu GitHub
5. Verifica tu email

### 3.2 Crear Servicio Web
1. En el dashboard de Render, haz clic en "+ New"
2. Selecciona "Web Service"
3. En "Deploy from a Git repository", haz clic en "Build and deploy from a Git repository"
4. Autoriza Render a GitHub si es necesario
5. Busca y selecciona **`rolfing-web-project`** de tu lista de repositorios

### 3.3 Configurar el Servicio

En la página de configuración, rellena con estos valores:

| Campo | Valor |
|-------|-------|
| **Name** | `rolfing-web` |
| **Environment** | `Java` |
| **Region** | Tu región más cercana (ej: Frankfurt) |
| **Plan** | `Free` |
| **Build Command** | `./gradlew clean build` |
| **Start Command** | `java -cp build/libs/rolfing.war com.rolfing.server.JettyServer` |

### 3.4 Agregar Variable de Entorno

1. Baja en la página a "Environment"
2. Haz clic en "Add Environment Variable"
3. Rellena:
   - **Key:** `PORT`
   - **Value:** `8080`

### 3.5 Deploy

1. Desplaza hacia el final de la página
2. Haz clic en **"Deploy Web Service"**
3. Espera (toma 5-10 minutos)

### 3.6 Tu Aplicación Está Viva

Cuando termine:

1. Render te muestra una URL como: `https://rolfing-web.onrender.com`
2. Haz clic en ella
3. ¡Deberías ver tu aplicación de Rolfing en línea! 🎉

---

## 📊 Estado del Deployment

Una vez desplegado, puedes:

- **Ver logs:** En Render → Tu servicio → Logs
- **Ver salud:** Dashboard (deberá estar verde ✅)
- **Acceder:** `https://rolfing-web.onrender.com`
- **Compartir:** Enviar URL a cualquiera

---

## 🎯 Resumen de URLs

**Tu Aplicación Estará En:**
```
https://rolfing-web.onrender.com
```

**Tu GitHub:**
```
https://github.com/[TU_USERNAME]/rolfing-web-project
```

**Panel de Render:**
```
https://dashboard.render.com
```

---

## ⚠️ Si Algo Sale Mal

### Error en Build
- Revisa los logs en Render
- Verifica que `gradlew` está en el repo
- Intenta recompilar localmente: `gradlew.bat clean build`

### Aplicación no inicia
- Revisa los logs en Render
- Verifica que `JettyServer.java` existe
- Verifica el Puerto (debe ser 8080 o usar variable PORT)

### No encuentra el repositorio
- Verifica que tu repo es **Public**
- Verifica que autorizaste a Render en GitHub
- Intenta desconectar y reconectar en Render

---

## ✅ Checklist Final

Antes de empezar, asegúrate de:

- [ ] Cuenta GitHub creada
- [ ] Cuenta Render creada
- [ ] Repositorio GitHub creado (`rolfing-web-project`)
- [ ] Código subido a GitHub (`git push`)
- [ ] Repositorio es **Public**
- [ ] Archivos visibles en GitHub

Después de desplegar:

- [ ] Deploy completó sin errores
- [ ] URL de Render activa
- [ ] Aplicación carga en navegador
- [ ] Todas las secciones visibles
- [ ] CSS y JavaScript funcionan

---

## 📞 Apoyo

Si necesitas ayuda en cualquier paso:

1. **Revisa los logs** en Render (te dirán qué está mal)
2. **Verifica que GitHub sea público**
3. **Intenta redespegar** (Deploy → Redeploy latest commit)
4. **Contacta si sigue fallando**

---

## 🎉 ¡Listo!

Una vez completado, tendrás:

✅ Tu código en GitHub (respaldo seguro)
✅ Tu aplicación en Render (en línea)
✅ URL pública para compartir
✅ Deploy automático (cada `git push` se redespliega)

**¡Vamos! Comienza con el Paso 1.** 🚀

---

*Guía creada: 13 de Abril de 2026*
