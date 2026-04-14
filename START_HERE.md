# 🚀 START HERE - Comienza Aquí

## ¡Bienvenido a tu Sitio Web de Rolfing!

Tu nuevo **sitio web profesional de Rolfing** ha sido creado exitosamente. Esta es tu guía de inicio rápido.

---

## ⚡ 5 Minutos para Ver tu Sitio en Vivo

### Paso 1: Abre PowerShell/Terminal

Navega a la carpeta del proyecto:
```bash
cd C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject
```

### Paso 2: Compila el Proyecto

```bash
gradlew.bat build
```

Espera a que termine. Verás: **BUILD SUCCESSFUL** ✅

### Paso 3: Inicia la Aplicación

```bash
gradlew.bat jettyRun
```

### Paso 4: Abre tu Navegador

```
http://localhost:8080/rolfing
```

## 🎉 ¡Listo! Tu sitio está corriendo.

---

## 📚 ¿Qué Encontrarás en tu Sitio?

### Página Principal (index.html)
- 🎯 Hero section con tu propuesta de valor
- ℹ️ Explicación de qué es Rolfing
- 🎬 **4 videos integrados** (2 educativos + 2 testimonios)
- 💬 **6 testimonios de pacientes**
- 📞 Formulario de contacto
- 🔗 Navegación profesional

### Página de Servicios (servicios.html)
- 💼 **6 servicios** diferentes con precios
- 📊 Tabla comparativa
- ❓ FAQ (Preguntas Frecuentes)
- 📞 Llamada a la acción

---

## 🎨 Personaliza Tu Sitio (10 minutos)

### 1. Cambiar Teléfono/Email

**Abre:** `src/main/webapp/index.html`

**Busca:** "Contactáctanos" (usa Ctrl+F)

**Reemplaza:**
```html
<p><a href="tel:+1234567890">+1 (234) 567-8900</a></p>
```

**Con tu número:**
```html
<p><a href="tel:+TU_NUMERO">TU_NUMERO</a></p>
```

### 2. Cambiar Colores (OPCIONAL)

**Abre:** `src/main/webapp/css/styles.css`

**Busca:** `:root {` (línea 7)

**Cambia:**
```css
--primary-color: #0066cc;      /* Azul actual → tu color */
--secondary-color: #00a86b;    /* Verde actual → tu color */
```

Colores útiles:
- Rojo: `#e74c3c`
- Púrpura: `#9b59b6`
- Teal: `#1abc9c`
- Naranja: `#f39c12`

### 3. Cambiar Videos

**Abre:** `src/main/webapp/index.html`

**Busca:** `youtube.com/embed/`

**Reemplaza el ID** con el tuyo:
```html
<!-- Cambiar de: -->
<iframe src="https://www.youtube.com/embed/dQw4w9WgXcQ">

<!-- A: -->
<iframe src="https://www.youtube.com/embed/TU_VIDEO_ID">
```

¿Cómo obtener el ID?
- Ve a YouTube
- Busca tu video
- URL: `https://youtube.com/watch?v=AQUI_ESTA_EL_ID`
- Copia solo la parte del ID

### 4. Cambiar Información General

**Abre:** `config.properties`

**Actualiza:**
```properties
business.name=Tu Nombre del Negocio
contact.phone1=Tu Teléfono
contact.email=tu@email.com
business.location=Tu Dirección
```

---

## 📖 Documentación Disponible

### Para Empezar Rápido
- **ESTE ARCHIVO** (Ahora estás leyéndolo) - Inicio en 5 minutos
- **QUICKSTART.md** - Guía de 5 minutos más detallada

### Para Entender Todo
- **README.md** - Descripción completa del proyecto
- **ESTRUCTURA_PROYECTO.md** - Estructura técnica

### Para Desplegar en Producción
- **DEPLOYMENT.md** - Guía de deployment

### Referencia Rápida
- **PROYECTO_COMPLETADO.md** - Resumen de qué se incluyó

---

## 🎬 Videos Integrados

Tu sitio incluye **4 videos de YouTube**:

1. ✅ **Video 1:** ¿Qué es Rolfing? - Introducción
2. ✅ **Video 2:** ¿Qué es Rolfing? - Técnicas  
3. ✅ **Video 3:** Testimonio - Paciente 1
4. ✅ **Video 4:** Testimonio - Paciente 2

**Puedes cambiarlos** por tus propios videos (ver sección anterior)

---

## 💬 Testimonios Incluidos

Se incluyeron **6 testimonios de ejemplo**:

1. María González - Ejecutiva
2. Juan Carlos López - Deportista
3. Sandra Rodríguez - Profesora
4. Roberto Martínez - Cirujano
5. Claudia Fernández - Diseñadora
6. Miguel Flores - Ingeniero

**Puedes editarlos** en `src/main/webapp/index.html` (búsca "Testimonios")

---

## 🛠️ Hacer Cambios y Recompilar

Después de personalizar:

```bash
# Detener la aplicación: Presiona Ctrl+C en la terminal

# Recompila
gradlew.bat build

# Vuelve a iniciar
gradlew.bat jettyRun

# Recarga tu navegador (F5)
```

---

## ✅ Tu Checklist

**Fase 1: Hoy**
- [ ] Descarga el proyecto
- [ ] Ejecuta `gradlew.bat build`
- [ ] Ejecuta `gradlew.bat jettyRun`
- [ ] Abre http://localhost:8080/rolfing
- [ ] Verifica que todo se cargue

**Fase 2: Esta Semana**
- [ ] Cambia teléfono/email
- [ ] Cambia colores (opcional)
- [ ] Añade tus videos
- [ ] Edita testimonios reales
- [ ] Actualiza precios en servicios.html

**Fase 3: Próximas Semanas**
- [ ] Compra un dominio
- [ ] Configura hospedaje (Hosting)
- [ ] Deploy en servidor
- [ ] Configura email
- [ ] Publica en redes sociales

---

## 🌐 Estructura de Archivos Clave

```
RolfingWebProject/
├── src/main/webapp/
│   ├── index.html           ← Página principal (EDITAR ESTO)
│   ├── servicios.html       ← Página de servicios (EDITAR ESTO)
│   ├── css/styles.css       ← Estilos (EDITAR COLORES AQUÍ)
│   └── js/script.js         ← Lógica JavaScript
├── README.md                ← Documentación completa
├── QUICKSTART.md            ← Guía rápida
├── DEPLOYMENT.md            ← Guía de producción
└── config.properties        ← Configuración general

```

---

## 🚨 Si Algo No Funciona

### Problema: "Port 8080 already in use"
**Solución:** 
- Otro programa usa el puerto 8080
- Cierra Chrome/navegadores abiertos
- O mata el proceso: `netstat -ano | findstr :8080`

### Problema: "Java not found"
**Solución:**
- Descarga Java: https://www.oracle.com/java/
- Instala JDK (no solo JRE)
- Reinicia PowerShell

### Problema: "Página en blanco"
**Solución:**
- Verifica que accedes a: `http://localhost:8080/rolfing` (con /rolfing)
- Abre DevTools (F12) y mira la consola
- Revisa la consola de PowerShell para errores

---

## 💡 Tips Útiles

1. **Recargar página sin caché:**
   - Presiona `Ctrl+Shift+R` en Chrome
   
2. **Ver código de la página:**
   - Presiona `F12` → pestaña "Elements"
   
3. **Ver errores JavaScript:**
   - Presiona `F12` → pestaña "Console"

4. **Cambiar puerto (si 8080 no funciona):**
   - Edita `build.gradle.kts`
   - Busca `httpPort`
   - Cambia a `httpPort = 9090`

---

## 📞 Tus Datos de Contacto (Actualizar)

Busca y reemplaza en los archivos:

| Dato | Buscar | Archivo |
|------|--------|---------|
| Teléfono | +1 (234) 567-8900 | index.html |
| Email | info@rolfing.com | index.html |
| Ubicación | Calle Principal #123 | index.html |
| Nombre | Rolfing Terapia | config.properties |

---

## 🎯 Tu Siguiente Paso

**→ Lee QUICKSTART.md** para instrucciones más detalladas

O simplemente:

```bash
cd RolfingWebProject
gradlew.bat build
gradlew.bat jettyRun
# Abre http://localhost:8080/rolfing
```

---

## 🎉 ¡Felicitaciones!

Tu **sitio web de Rolfing profesional y moderno** está listo.

Incluye:
- ✅ 2 páginas HTML completas
- ✅ Diseño responsivo
- ✅ 4 videos integrados
- ✅ 6 testimonios
- ✅ Formulario de contacto
- ✅ 6 servicios con precios
- ✅ FAQ interactivo
- ✅ Documentación completa
- ✅ Listo para producción

---

**¡Ahora es tu turno de personalizar y compartir con el mundo! 🚀**

```
Última actualización: 13 de Abril, 2026
Versión: 1.0.0
Estado: ✅ LISTO PARA USAR
```
