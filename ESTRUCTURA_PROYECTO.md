# 📁 Estructura del Proyecto RolfingWebProject

## 📊 Árbol de Directorios Completo

```
RolfingWebProject/
│
├── 📄 build.gradle.kts              # Configuración de compilación Gradle
├── 📄 settings.gradle.kts           # Configuración de módulos Gradle
├── 📄 gradle.properties             # Propiedades del proyecto Gradle
│
├── 📄 README.md                     # Documentación principal
├── 📄 QUICKSTART.md                 # Guía rápida de inicio
├── 📄 DEPLOYMENT.md                 # Guía de deployment/producción
├── 📄 ESTRUCTURA_PROYECTO.md        # Este archivo
├── 📄 config.properties             # Archivo de configuración
├── 📄 .gitignore                    # Archivos a ignorar en Git
│
├── 📁 gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar       # JAR del wrapper de Gradle
│       └── gradle-wrapper.properties # Propiedades del wrapper
│
├── 📁 src/
│   ├── main/
│   │   ├── webapp/                  # Archivos web públicos
│   │   │   ├── 📄 index.html        # ⭐ Página principal (HOME)
│   │   │   ├── 📄 servicios.html    # 📋 Página de servicios
│   │   │   │
│   │   │   ├── 📁 css/
│   │   │   │   └── 📄 styles.css    # 🎨 Estilos CSS personalizados
│   │   │   │
│   │   │   ├── 📁 js/
│   │   │   │   └── 📄 script.js     # 🔧 Scripts JavaScript
│   │   │   │
│   │   │   └── 📁 WEB-INF/
│   │   │       └── 📄 web.xml       # Configuración de la app web
│   │   │
│   │   └── java/                    # Código fuente Java
│   │       └── com/rolfing/
│   │           │
│   │           ├── servlet/
│   │           │   └── ContactServlet.java     # Endpoint API /contact
│   │           │
│   │           └── model/
│   │               └── Contact.java           # Modelo de datos
│   │
│   └── test/                        # Tests unitarios
│       └── java/
│           └── com/rolfing/
│               └── (tests aquí)
│
├── 📁 build/                        # Archivos compilados (Auto-generado)
│   └── libs/
│       └── rolfing.war              # Archivo WAR para deployment
│
├── 📁 .gradle/                      # Cache de Gradle (Auto-generado)
│
└── 📁 .idea/                        # Configuración de IntelliJ IDEA


```

## 🔍 Descripción de Archivos Clave

### 📄 Archivos de Configuración

| Archivo | Propósito |
|---------|----------|
| `build.gradle.kts` | Define dependencias, plugins y tareas de compilación |
| `settings.gradle.kts` | Configura módulos del proyecto |
| `gradle.properties` | Propiedades de JVM y Gradle |
| `config.properties` | Configuración específica de la aplicación |

### 🌐 Archivos Web (Frontend)

| Archivo | Propósito |
|---------|----------|
| `index.html` | Página principal con hero section, videos, testimonios |
| `servicios.html` | Catálogo de servicios con precios y comparativa |
| `css/styles.css` | Estilos Bootstrap personalizados y animaciones |
| `js/script.js` | Validación, animaciones, manejo de eventos |

### ☕ Archivos Java (Backend)

| Archivo | Propósito |
|---------|----------|
| `ContactServlet.java` | Procesa solicitudes POST del formulario de contacto |
| `Contact.java` | Clase modelo que representa un contacto |

### ⚙️ Archivos de Configuración Web

| Archivo | Propósito |
|---------|----------|
| `WEB-INF/web.xml` | Descriptor de deployment de la aplicación |

## 🎨 Secciones de la Página Principal (index.html)

```
┌─────────────────────────────────────────────────────┐
│ 🔝 NAVBAR (Sticky)                                  │
│  - Logo + Menú de navegación                        │
│  - Links: Inicio, ¿Qué es Rolfing?, Videos, etc.   │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 🎯 HERO SECTION                                      │
│  - Título principal                                 │
│  - Descripción                                      │
│  - Botones CTA (Call To Action)                     │
│  - Imagen decorativa                                │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ ℹ️ SECCIÓN: ¿QUÉ ES ROLFING?                       │
│  - Descripción de la terapia                        │
│  - 4 Feature boxes (Alivio, Postura, etc.)         │
│  - Imagen ilustrativa                               │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 🎬 SECCIÓN: VIDEOS                                   │
│  - 4 videos embebidos de YouTube                    │
│    • 2 videos educativos sobre Rolfing              │
│    • 2 videos de testimonios de pacientes           │
│  - Descripción de cada video                        │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 💬 SECCIÓN: TESTIMONIOS                              │
│  - 6 tarjetas de testimonios                        │
│  - Foto del paciente (avatar)                       │
│  - Nombre y profesión                               │
│  - Calificación de estrellas                        │
│  - Texto del testimonio                             │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 📢 SECCIÓN: CTA (Llamada a la Acción)              │
│  - Mensaje motivacional                             │
│  - Botón para agendar                               │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 📞 SECCIÓN: CONTACTO                                 │
│  - 3 tarjetas de información (teléfono, etc.)      │
│  - Formulario de contacto                           │
│    • Validación de campos                           │
│    • Manejo de errores                              │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 🔗 FOOTER                                            │
│  - Enlaces de navegación                            │
│  - Información de servicios                         │
│  - Formulario de newsletter                         │
│  - Redes sociales                                   │
│  - Derechos de autor                                │
└─────────────────────────────────────────────────────┘
```

## 🎬 Secciones de la Página de Servicios (servicios.html)

```
┌─────────────────────────────────────────────────────┐
│ 🔝 NAVBAR                                            │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 🎯 HERO SECTION                                      │
│  - Título: "Nuestros Servicios"                     │
│  - Subtítulo descriptivo                            │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 💼 CATÁLOGO DE SERVICIOS (6 cards)                 │
│  1. Sesión Individual                               │
│  2. Paquete de 10 Sesiones (DESTACADO)             │
│  3. Rolfing Deportivo                               │
│  4. Sesiones Grupales                               │
│  5. Consulta Inicial (GRATIS)                       │
│  6. Talleres y Workshops                            │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 📊 TABLA COMPARATIVA                                 │
│  - Características vs. Servicios                     │
│  - Checkmarks para características incluidas         │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ ❓ PREGUNTAS FRECUENTES (FAQ)                       │
│  - Acordeón colapsable                              │
│  - 5 preguntas típicas                              │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 📢 SECCIÓN: CTA                                      │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│ 🔗 FOOTER                                            │
└─────────────────────────────────────────────────────┘
```

## 🎨 Paleta de Colores

```
╔════════════════════════════════════════════╗
║              COLORES DEL PROYECTO          ║
╠════════════════════════════════════════════╣
║ 🔵 Primario    : #0066cc (Azul profesional)║
║ 🟢 Secundario  : #00a86b (Verde salud)     ║
║ ⬛ Oscuro       : #1a1a1a (Muy oscuro)     ║
║ ⬜ Claro       : #f8f9fa (Gris claro)     ║
║ ⚪ Blanco      : #ffffff                   ║
║ ◼️ Gris        : #666666                   ║
╚════════════════════════════════════════════╝
```

## 🔧 Stack Tecnológico

### Frontend
- **HTML5** - Estructura semántica
- **CSS3** - Estilos avanzados con animaciones
- **Bootstrap 5** - Framework responsivo
- **Font Awesome 6** - Iconografía profesional
- **JavaScript ES6** - Interactividad

### Backend
- **Java 11+** - Lenguaje principal
- **Servlets** - Manejo de HTTP
- **Gradle** - Build automation

### Deployment
- **WAR** - Formato de empaquetamiento
- **Tomcat/Jetty** - Servidores de aplicación

## 📦 Tamaños Aproximados

| Componente | Tamaño |
|-----------|--------|
| HTML (index + servicios) | ~50 KB |
| CSS personalizado | ~15 KB |
| JavaScript | ~8 KB |
| JAR de dependencias | ~5 MB |
| WAR compilado | ~20 MB |

## 🔄 Flujo de Datos

```
Usuario (Navegador)
      ↓
index.html / servicios.html
      ↓
CSS (Bootstrap + Personalizado)
      ↓
JavaScript (Validación, Animaciones)
      ↓
[Formulario de Contacto]
      ↓
POST → /api/contact
      ↓
ContactServlet.java
      ↓
Contact.java (Modelo)
      ↓
[Guardar o Enviar Email]
      ↓
Respuesta JSON → Navegador
```

## 🚀 Flujo de Compilación (Gradle)

```
Código Fuente
    ↓
./gradlew build
    ↓
Compilación Java (src/main/java)
    ↓
Empaquetado WAR (index.html, css, js, clases compiladas)
    ↓
build/libs/rolfing.war
    ↓
Deploy en Tomcat/Jetty
    ↓
http://localhost:8080/rolfing
```

## 📝 Convenciones de Nombres

- **Carpetas:** Minúsculas con guion bajo (`main_content`)
- **Archivos CSS:** Minúsculas con guion (`styles.css`)
- **Archivos JS:** Minúsculas con guion (`script.js`)
- **Clases Java:** PascalCase (`ContactServlet.java`)
- **Métodos Java:** camelCase (`handleFormSubmit()`)
- **IDs HTML:** lowercase-with-dash (`#contact-form`)
- **Classes HTML:** lowercase-with-dash (`.hero-section`)

## ✅ Checklist de Desarrollo

- [ ] Proyecto compilado sin errores
- [ ] Páginas HTML se cargan correctamente
- [ ] Estilos CSS se aplican
- [ ] Scripts JavaScript funcionan
- [ ] Formulario valida correctamente
- [ ] Responsive en móvil, tablet, desktop
- [ ] Videos se reproducen
- [ ] Links de navegación funcionan
- [ ] WAR se genera correctamente

## 📚 Archivos de Documentación

| Documento | Contenido |
|-----------|----------|
| `README.md` | Descripción general y características |
| `QUICKSTART.md` | Guía rápida de 5 minutos |
| `DEPLOYMENT.md` | Guía completa de deployment |
| `ESTRUCTURA_PROYECTO.md` | Este archivo (estructura del proyecto) |
| `config.properties` | Configuración de la aplicación |

---

**Nota:** Los directorios `build/` y `.gradle/` se generan automáticamente al compilar. No necesitan ser modificados manualmente.

