# 🚀 Guía de Deployment en Render - Rolfing Web Project

## 📋 Introducción

Esta guía te mostrará cómo desplegar la aplicación web de Rolfing en **Render**, una plataforma de hosting gratuita y confiable que soporta aplicaciones Java.

---

## ✅ Requisitos Previos

- ✅ Cuenta de GitHub (gratuita)
- ✅ Cuenta de Render (gratuita) - [Crear cuenta](https://render.com)
- ✅ Git instalado en tu máquina
- ✅ El repositorio local ya inicializado

---

## 🔧 Paso 1: Preparar el Repositorio Local

### 1.1 Verificar Git
```bash
cd "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject"
git status
```

Deberías ver que el proyecto está en el repositorio.

### 1.2 Crear Repositorio en GitHub

1. Abre [GitHub.com](https://github.com)
2. Inicia sesión o crea una cuenta
3. Haz clic en "New repository"
4. Nombre: `rolfing-web-project`
5. Descripción: "Sitio web de Rolfing - Terapia Muscular Integral"
6. Selecciona "Public" (para que Render pueda acceder)
7. Clic en "Create repository"

### 1.3 Conectar Repositorio Local a GitHub

Copia los comandos que GitHub te proporciona. Será algo como:

```bash
git remote add origin https://github.com/[TU_USUARIO]/rolfing-web-project.git
git branch -M main
git push -u origin main
```

---

## 🌐 Paso 2: Crear Cuenta en Render

1. Visita [Render.com](https://render.com)
2. Haz clic en "Get Started"
3. Selecciona "Sign up with GitHub"
4. Autoriza Render para acceder a tu cuenta de GitHub
5. Verifica tu email

---

## 🚀 Paso 3: Desplegar en Render

### 3.1 Crear Nuevo Servicio Web

1. En el dashboard de Render, haz clic en "+ New"
2. Selecciona "Web Service"
3. Selecciona "Deploy from a Git repository"
4. Autoriza Render para GitHub si es necesario
5. Busca y selecciona `rolfing-web-project`

### 3.2 Configurar Opciones

En la página de configuración:

**Name:** `rolfing-web` (o el nombre que prefieras)

**Environment:** `Java` (o Auto-detect)

**Region:** Selecciona el más cercano a ti (ej: Frankfurt para Europa)

**Plan:** `Free` (tier gratuito)

**Build Command:**
```bash
./gradlew clean build
```

**Start Command:**
```bash
java -cp build/libs/rolfing.war com.rolfing.server.JettyServer
```

**Environment Variables:**
Crea una variable:
- Key: `PORT`
- Value: `8080`

### 3.3 Desplegar

1. Desplaza hacia abajo
2. Haz clic en "Deploy Web Service"
3. Espera a que termine el deployment (5-10 minutos)

---

## ✅ Paso 4: Verificar el Deployment

Una vez completado:

1. Render te proporcionará una URL como: `https://rolfing-web.onrender.com`
2. Abre esa URL en tu navegador
3. Deberías ver tu aplicación funcionando

---

## 📊 Estado del Deployment

### Monitorear Logs

En el dashboard de Render:
1. Haz clic en tu servicio
2. Ve a la pestaña "Logs"
3. Aquí puedes ver los logs de compilación y ejecución

### Revisar Salud del Servicio

1. Ve a la pestaña "Dashboard"
2. Verás el estado del servicio (verde = funcionando)

---

## 🔄 Actualizar la Aplicación

Después de hacer cambios locales:

```bash
# Hacer cambios en los archivos
# Por ejemplo, editar index.html

# Confirmar cambios
git add .
git commit -m "Actualizar contenido de Rolfing"

# Subir a GitHub
git push origin main
```

Render detectará los cambios automáticamente y redesplegará la aplicación.

---

## ⚙️ Configuración Avanzada

### Variables de Entorno

Para agregar más variables en Render:

1. Ve a tu servicio
2. Haz clic en "Environment"
3. Agrega nuevas variables según sea necesario

Ejemplo para una base de datos:
```
DATABASE_URL = postgres://user:pass@host:5432/dbname
```

### Dominio Personalizado

Para usar tu propio dominio:

1. En Render, ve a "Settings"
2. Haz clic en "Custom Domain"
3. Agrega tu dominio
4. Sigue las instrucciones para actualizar los DNS

---

## 🐛 Solucionar Problemas

### La aplicación no inicia

**Problema:** Ves errores en los logs

**Solución:**
1. Revisa los logs en Render
2. Verifica que `build.gradle.kts` esté correcto
3. Verifica que `JettyServer.java` exista
4. Haz un rebuild:
   - Ve a Settings
   - Haz clic en "Manual Deploy"
   - Selecciona "main"

### Error: "Port already in use"

**Problema:** El puerto 8080 está en uso

**Solución:**
En el Start Command, Render asigna automáticamente un puerto. Cambia:
```bash
java -cp build/libs/rolfing.war -Dport=$PORT com.rolfing.server.JettyServer
```

Y actualiza `JettyServer.java` para leer la variable PORT.

### La compilación falla

**Problema:** Gradle no puede compilar

**Solución:**
1. Verifica que `gradlew` tiene permisos de ejecución
2. Intenta compilar localmente: `./gradlew.bat clean build`
3. Revisa los logs para mensajes de error específicos

---

## 💾 Respaldo y Recuperación

### Respaldar tu Código

Tu código está en GitHub, que es un respaldo automático.

Para crear un respaldo adicional:
```bash
# Crear una rama de backup
git branch backup-2026-04-13
git push origin backup-2026-04-13
```

### Restaurar desde GitHub

Si algo va mal en Render:
1. Puedes simplemente recriar el servicio en Render
2. Render volverá a descargar el código de GitHub

---

## 📱 Acceso desde Dispositivos

### URL Base
```
https://rolfing-web.onrender.com
```

### Secciones Disponibles

- Página Principal: `https://rolfing-web.onrender.com`
- Servicios: `https://rolfing-web.onrender.com/servicios.html`
- Contacto: `https://rolfing-web.onrender.com#contacto`

---

## 🎯 Próximos Pasos

1. **Personalizar contenido:**
   - Edita `src/main/webapp/index.html`
   - Haz commit: `git commit -m "Personalizar contenido"`
   - Haz push: `git push origin main`
   - Render se redesplegará automáticamente

2. **Agregar testimonios reales:**
   - Edita la sección de testimonios en HTML
   - Commit y push

3. **Cambiar información de contacto:**
   - Actualiza emails y teléfono en HTML
   - Commit y push

4. **Configurar SSL:**
   - Render incluye SSL por defecto (HTTPS)
   - Tu sitio es seguro automáticamente

---

## 📞 Soporte

### Documentación de Render
- [Render Docs](https://render.com/docs)
- [Deploy from Git](https://render.com/docs/deploy-from-git)
- [Free Tier](https://render.com/free)

### Documentación del Proyecto
- [README.md](README.md) - Información general
- [TEST_REPORT.md](TEST_REPORT.md) - Detalles técnicos
- [TESTING_GUIDE.md](TESTING_GUIDE.md) - Cómo probar

---

## ✨ Información Importante

### Límites del Plan Gratuito de Render

- Máximo 1 servicio web gratis
- 750 horas de ejecución por mes
- Se duerme después de 15 minutos sin solicitudes
- Tiempo de activación: 30 segundos (desde suspensión)
- Ancho de banda: 100 GB/mes

### Mejora a Plan Pago

Si necesitas más:
1. Ve a Settings en Render
2. Haz clic en "Upgrade Plan"
3. Selecciona el plan que prefieras

---

**¡Tu aplicación está lista para el mundo! 🌍**

*Última actualización: 13 de Abril de 2026*
