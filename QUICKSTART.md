# 🚀 QUICKSTART - Rolfing Web Project

¡Empieza en 5 minutos! Sigue estos pasos simples para poner en funcionamiento tu sitio web de Rolfing.

## 1️⃣ Requisitos Mínimos

- Java JDK 11+ ([Descargar](https://www.oracle.com/java/technologies/downloads/))
- Un editor de texto o IDE (VS Code, IntelliJ, Eclipse)

## 2️⃣ Descargar y Abrir Proyecto

```bash
# Navegar a la carpeta del proyecto
cd RolfingWebProject

# Abrir en tu IDE favorito (IntelliJ IDEA ejemplo)
idea .
```

## 3️⃣ Compilar el Proyecto

```bash
# En Windows
gradlew.bat build

# En Mac/Linux
./gradlew build
```

**✅ Si ves "BUILD SUCCESSFUL" - ¡Estás listo!**

## 4️⃣ Ejecutar la Aplicación

**Opción A: Servidor Integrado (Recomendado para desarrollo)**

```bash
./gradlew jettyRun
```

**Opción B: Deploy en Tomcat**

1. Descarga [Tomcat 9](https://tomcat.apache.org/download-90.cgi)
2. Copia `build/libs/rolfing.war` a `tomcat/webapps/`
3. Ejecuta `tomcat/bin/catalina.bat run` (Windows) o `./catalina.sh run` (Mac/Linux)

## 5️⃣ Acceder a la Web

```
http://localhost:8080/rolfing
```

## 📁 Estructura Rápida

```
├── index.html           👈 Página principal
├── servicios.html       👈 Página de servicios
├── css/styles.css       👈 Estilos personalizados
├── js/script.js         👈 Interactividad
└── api/contact          👈 Endpoint del formulario
```

## 🎨 Personalizar Contenido

### 1. Cambiar Información de Contacto

**Archivo:** `src/main/webapp/index.html`

```html
<!-- Busca la sección "Contacto" y actualiza -->
<p><a href="tel:+1234567890">TU_TELEFONO</a></p>
<p><a href="mailto:info@rolfing.com">TU_EMAIL</a></p>
```

### 2. Cambiar Colores

**Archivo:** `src/main/webapp/css/styles.css`

```css
:root {
    --primary-color: #0066cc;      /* Cambiar aquí */
    --secondary-color: #00a86b;    /* Y aquí */
}
```

### 3. Cambiar Videos

**Archivo:** `src/main/webapp/index.html` (Sección Videos)

```html
<!-- Reemplaza los IDs de YouTube -->
<iframe src="https://www.youtube.com/embed/TU_VIDEO_ID">
```

Usa videos de YouTube: obtén el ID del URL
- URL: `https://youtube.com/watch?v=AQUI_ESTA_EL_ID`

## 🎯 Próximos Pasos

### ✅ Fase 1: Básico (Ahora)
- [x] Proyecto creado
- [x] Páginas HTML
- [x] Estilos CSS
- [ ] Personalizar contenido

### ⏳ Fase 2: Intermediario (Próximo)
- [ ] Configurar base de datos
- [ ] Integrar email de contactos
- [ ] Añadir testimonios reales
- [ ] Optimizar SEO

### 🚀 Fase 3: Avanzado (Futuro)
- [ ] Booking/agendamiento online
- [ ] Panel de administración
- [ ] Blog
- [ ] Analytics

## 🆘 Solucionar Problemas

### Error: "Java no reconocido"
```bash
# Verifica que Java está instalado
java -version

# Si no está instalado, descargalo de:
# https://www.oracle.com/java/technologies/downloads/
```

### Error: "Puerto 8080 en uso"
```bash
# Cambia a otro puerto en build.gradle.kts o espera a que se libere
# O mata el proceso existente
# Windows: netstat -ano | findstr :8080
# Mac/Linux: lsof -i :8080
```

### Página en blanco
```bash
# 1. Verifica la consola para errores
# 2. Abre DevTools (F12) y mira la pestaña Console
# 3. Verifica que apuntas a: http://localhost:8080/rolfing
```

## 📞 Contacto y Soporte

- 📧 Email: info@rolfing.com
- 📱 Teléfono: +1 (234) 567-8900
- 🌐 Web: http://localhost:8080/rolfing

## 📚 Documentación Completa

Para detalles avanzados, consulta:
- `README.md` - Documentación completa
- `DEPLOYMENT.md` - Guía de producción
- `build.gradle.kts` - Configuración Gradle

## ⚡ Tips Útiles

1. **Reload automático en desarrollo:**
   - Usa IntelliJ "Run" menu → "Rerun" (Ctrl+F5)
   - O instala "Live Reload" en tu navegador

2. **Ver código JavaScript en tiempo real:**
   - Abre DevTools (F12)
   - Ve a Sources para debugging

3. **Cambiar puerto:**
   - En `build.gradle.kts`: `httpPort = 9090`
   - O en Tomcat: edita `conf/server.xml`

4. **Modo oscuro (CSS):**
   - Añade una clase `.dark-mode` a los elementos
   - Personaliza en `styles.css`

---

**¡Listo! Tu sitio web está corriendo. 🎉**

**Próximo paso:** Personaliza el contenido con tu información y ¡comienza a promocionar tus servicios!

