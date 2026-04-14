# Rolfing Web Project - Sitio Web de Promoción de Terapia Muscular

Una aplicación web moderna, responsiva y profesional para promocionar servicios de Rolfing (Terapia Muscular Integral).

## 🎯 Características

### ✨ Secciones Principales

1. **Inicio (Hero Section)**
   - Banner llamativo con descripción de servicios
   - Call-to-action buttons para acciones principales
   - Diseño moderno y atractivo

2. **¿Qué es Rolfing?**
   - Información detallada sobre la terapia
   - Beneficios principales con iconos
   - Descripción de técnicas y metodología

3. **Sección de Videos**
   - 2 videos educativos: "¿Qué es Rolfing?" (Partes 1 y 2)
   - Videos de testimonios de pacientes
   - Embed de YouTube con reproducción integrada
   - Tarjetas responsivas con descripción

4. **Testimonios**
   - 6 testimonios de pacientes reales
   - Sistema de valoración de estrellas
   - Perfiles con datos del paciente
   - Diseño de tarjetas elegante

5. **Contacto**
   - Formulario de contacto completo
   - Información de ubicación, teléfono y email
   - Validación de campos en tiempo real
   - Respuestas visuales al usuario

6. **Footer**
   - Enlaces rápidos de navegación
   - Información de servicios
   - Formulario newsletter
   - Social media links
   - Información legal

## 🛠️ Tecnologías Utilizadas

### Frontend
- **HTML5**: Estructura semántica moderna
- **CSS3**: Estilos avanzados con animaciones
- **Bootstrap 5**: Framework responsivo
- **Font Awesome 6**: Iconografía profesional
- **JavaScript ES6**: Interactividad y validaciones

### Backend
- **Java 11+**: Lenguaje de programación
- **Servlets**: Manejo de solicitudes HTTP
- **Gradle**: Build automation y gestión de dependencias
- **Jetty/Tomcat**: Servidores de aplicación soportados

## 📁 Estructura del Proyecto

```
RolfingWebProject/
├── build.gradle.kts              # Configuración de Gradle
├── settings.gradle.kts           # Configuración de módulos
├── gradle.properties             # Propiedades del proyecto
├── README.md                     # Este archivo
└── src/
    ├── main/
    │   ├── webapp/               # Archivos web
    │   │   ├── index.html        # Página principal
    │   │   ├── css/
    │   │   │   └── styles.css    # Estilos personalizados
    │   │   └── js/
    │   │       └── script.js     # Scripts JavaScript
    │   └── java/                 # Código Java
    │       └── com/rolfing/
    │           ├── servlet/
    │           │   └── ContactServlet.java
    │           └── model/
    │               └── Contact.java
    └── test/                     # Pruebas unitarias
```

## 🚀 Instalación y Configuración

### Requisitos Previos
- Java JDK 11 o superior
- Gradle 6.0 o superior (opcional, viene con wrapper)
- IDE: IntelliJ IDEA, Eclipse o Visual Studio Code

### Pasos de Instalación

1. **Clonar o descargar el proyecto**
   ```bash
   cd RolfingWebProject
   ```

2. **Compilar el proyecto**
   ```bash
   ./gradlew build
   ```
   (En Windows: `gradlew.bat build`)

3. **Ejecutar en servidor local**
   
   **Opción A - Usando Gradle:**
   ```bash
   ./gradlew run
   ```

   **Opción B - Deployar WAR a Tomcat:**
   - Compilar: `./gradlew build`
   - Copiar `build/libs/rolfing.war` a la carpeta `webapps` de Tomcat
   - Iniciar Tomcat

4. **Acceder a la aplicación**
   ```
   http://localhost:8080/rolfing
   ```

## 📋 Características Técnicas

### Responsividad
- Diseño mobile-first
- Breakpoints: 576px, 768px, 992px, 1200px, 1400px
- Totalmente optimizado para todos los dispositivos

### Rendimiento
- CSS minificado en producción
- Carga de CDN para Bootstrap y Font Awesome
- Lazy loading de videos
- Optimización de imágenes

### Seguridad
- Validación de datos en frontend y backend
- Sanitización de inputs
- CSRF protection preparada
- Headers de seguridad configurables

### Accesibilidad
- Etiquetas HTML semánticas
- Atributos ARIA
- Contraste de colores WCAG compatible
- Navegación por teclado

### SEO
- Meta tags optimizados
- URLs amigables
- Estructura HTML semántica
- Mobile-friendly

## 🎨 Diseño y UX

### Paleta de Colores
- **Primario**: #0066cc (Azul profesional)
- **Secundario**: #00a86b (Verde salud)
- **Oscuro**: #1a1a1a
- **Claro**: #f8f9fa

### Tipografía
- Font Family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
- Pesos: 400, 500, 600, 700

### Animaciones
- Fade-in en scroll
- Hover effects en botones y tarjetas
- Transiciones suaves
- Floating animations

## 📞 Funcionalidades Adicionales

### Formulario de Contacto
- Validación de campos en tiempo real
- Respuestas visuales al usuario
- Simulación de envío (listo para backend)
- Manejo de errores

### Navegación
- Navbar sticky
- Smooth scrolling
- Enlaces de anclaje internos
- Collapse en móvil

### Componentes Interactivos
- Cards con hover effects
- Modal-ready (Bootstrap)
- Tooltips y popovers preparados
- Carousels configurables

## 🔧 Configuración Personalizada

### Cambiar Información de Contacto
Edita `index.html` sección de contacto:
```html
<p><a href="tel:+1234567890">+1 (234) 567-8900</a></p>
<p><a href="mailto:info@rolfing.com">info@rolfing.com</a></p>
```

### Cambiar Videos
En la sección de videos, reemplaza los ID de YouTube:
```html
<iframe src="https://www.youtube.com/embed/AQUI_TU_VIDEO_ID" ...>
```

### Personalizar Colores
Edita las variables CSS en `styles.css`:
```css
:root {
    --primary-color: #0066cc;
    --secondary-color: #00a86b;
}
```

## 🧪 Testing

### Pruebas Manuales
1. Verificar responsividad en diferentes tamaños
2. Probar formulario con datos válidos e inválidos
3. Validar navegación entre secciones
4. Comprobar reproductor de videos

### Pruebas en Navegadores
- Chrome/Chromium ✓
- Firefox ✓
- Safari ✓
- Edge ✓

## 📱 Compatibilidad

### Navegadores
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

### Dispositivos
- Desktop (1920x1080 y superiores)
- Laptop (1366x768)
- Tablet (768x1024)
- Mobile (320x568)

## 🚢 Deployment

### En Local
```bash
./gradlew build
./gradlew run
```

### En Servidor Producción

1. **Compilar WAR**
   ```bash
   ./gradlew build
   ```

2. **Deployar en Tomcat/Jetty**
   - Copiar WAR a webapps/
   - Iniciar servidor

3. **Configurar SSL**
   - Obtener certificado SSL
   - Configurar en servidor de aplicación

4. **Optimizar**
   - Minificar CSS/JS en producción
   - Configurar caché
   - Habilitar GZIP compression

## 📈 Futuras Mejoras

- [ ] Panel de administración para gestionar testimonios
- [ ] Sistema de agendamiento de citas
- [ ] Blog con artículos sobre Rolfing
- [ ] Integración con email marketing
- [ ] Chat en vivo
- [ ] Aplicación móvil nativa
- [ ] Integraciones con redes sociales
- [ ] Analytics avanzados

## 📄 Licencia

Este proyecto está disponible bajo la licencia MIT.

## 👨‍💻 Autor

Desarrollado como una solución profesional para promoción de servicios de Rolfing.

## 📧 Soporte

Para soporte y consultas, contacta a través del formulario en la web.

---

**Hecho con ❤️ para promotores de salud y bienestar**
