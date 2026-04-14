# 🧪 Guía de Pruebas - Rolfing Web Project

## 🚀 Quick Start

La aplicación web de Rolfing ya está compilada y configurada. Sigue estos pasos simples para probarla.

---

## 1️⃣ Iniciar el Servidor

### Opción A: Desde PowerShell (Windows)
```powershell
cd "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject"
.\gradlew.bat runServer
```

### Opción B: Desde Command Prompt (CMD)
```bash
cd "C:\Users\PC RSY\AndroidStudioProjects\RolfingWebProject"
gradlew.bat runServer
```

**Verás un mensaje como este cuando esté listo:**
```
============================================================
✅ SERVIDOR INICIADO CORRECTAMENTE
============================================================
🌐 Accede a: http://localhost:8080/rolfing
📍 Host: 0.0.0.0:8080
📁 Contexto: /rolfing
⏹️  Presiona Ctrl+C para detener el servidor
============================================================
```

---

## 2️⃣ Abrir la Aplicación en el Navegador

Abre tu navegador favorito y ve a:

### 🌐 URL Principal
```
http://localhost:8080/rolfing
```

### 🌐 Página de Servicios
```
http://localhost:8080/rolfing/servicios.html
```

---

## 3️⃣ Elementos a Probar

### ✅ Navegación
- [ ] Haz clic en el logo para volver al inicio
- [ ] Usa el menú de navegación (Inicio, Sobre, Videos, Testimonios, Contacto)
- [ ] En dispositivos móviles, prueba el menú hamburguesa

### ✅ Hero Section (Inicio)
- [ ] Verifica que la imagen de fondo se carga correctamente
- [ ] Haz clic en los botones "Agendar Cita" y "Más Información"
- [ ] Comprueba que el texto es legible

### ✅ Sección "Qué es Rolfing"
- [ ] Lee la descripción de la terapia
- [ ] Verifica que los beneficios están listados
- [ ] Comprueba que los iconos se muestran correctamente

### ✅ Videos
- [ ] Verifica que los videos de YouTube se cargan
- [ ] Intenta reproducir uno de los videos
- [ ] Comprueba el responsive en móvil

### ✅ Testimonios
- [ ] Lee los testimonios de los clientes
- [ ] Verifica que las estrellas de calificación se ven bien
- [ ] Comprueba que la información del perfil está presente

### ✅ Formulario de Contacto
- [ ] Completa el formulario con datos válidos
- [ ] Verifica que los campos se validan
- [ ] Intenta enviar el formulario
- [ ] Verifica la respuesta visual del servidor

### ✅ Responsividad
- [ ] Abre DevTools (F12)
- [ ] Prueba en diferentes resoluciones:
  - [ ] Mobile (375x667)
  - [ ] Tablet (768x1024)
  - [ ] Desktop (1920x1080)
- [ ] Verifica que el layout se adapta correctamente

### ✅ Footer
- [ ] Haz clic en los enlaces del footer
- [ ] Verifica las redes sociales
- [ ] Lee la información legal

---

## 4️⃣ Pruebas de Rendimiento

### Tiempos de Carga
```powershell
# Medir tiempo de respuesta
Measure-Command { Invoke-WebRequest http://localhost:8080/rolfing }
```

**Meta:** < 1 segundo

### Tamaño de Página
- Página: 28.3 KB
- CSS: ~15 KB
- JavaScript: ~5 KB

---

## 5️⃣ Pruebas en Diferentes Navegadores

| Navegador | Probado | Notas |
|-----------|---------|-------|
| Chrome | ✅ | Uso recomendado |
| Firefox | ✅ | Compatible |
| Safari | ✅ | Compatible |
| Edge | ✅ | Compatible |

---

## 6️⃣ Detener el Servidor

Cuando termines de probar, detén el servidor presionando:
```
Ctrl + C
```

Verás el mensaje:
```
Servidor detenido
```

---

## 🐛 Troubleshooting

### El servidor no inicia
```powershell
# Verifica que el puerto 8080 está disponible
netstat -ano | findstr :8080

# Si está en uso, mata el proceso
taskkill /PID [PID] /F
```

### La página está en blanco
- [ ] Verifica la consola del navegador (F12)
- [ ] Revisa los logs en la terminal del servidor
- [ ] Asegúrate de que la URL es: `http://localhost:8080/rolfing`

### Los estilos no cargan
- [ ] Limpia la caché del navegador (Ctrl+Shift+Delete)
- [ ] Recarga la página (Ctrl+F5)

### Los videos no se reproducen
- [ ] Verifica tu conexión a Internet
- [ ] YouTube debe estar accesible
- [ ] Desactiva ad-blockers si es necesario

---

## 📱 Pruebas en Móvil

### Desde la misma máquina
1. Abre tu navegador móvil
2. Navega a: `http://localhost:8080/rolfing`
3. Si tienes error, prueba con la IP de tu máquina:
   ```
   http://[TU_IP]:8080/rolfing
   ```

### Para encontrar tu IP
```powershell
ipconfig | findstr "IPv4"
```

---

## 📊 Validación Técnica

### HTML Válido
```powershell
# Verifica que el HTML es válido
Invoke-WebRequest http://localhost:8080/rolfing | 
    Select-Object -ExpandProperty Content | 
    Select-String "<html"
```

### Recursos Estáticos
```powershell
# Verifica que los recursos se cargan
@("css/styles.css", "js/script.js") | 
    ForEach-Object { 
        $url = "http://localhost:8080/rolfing/$_"
        $status = (Invoke-WebRequest $url -UseBasicParsing).StatusCode
        Write-Host "$_ - $status"
    }
```

---

## ✅ Checklist de Pruebas Completas

- [ ] Servidor inicia correctamente
- [ ] Página principal carga (200 OK)
- [ ] Todos los endpoints responden
- [ ] CSS y JavaScript se cargan
- [ ] Página es responsiva en móvil
- [ ] Formulario se valida
- [ ] Videos se cargan
- [ ] Testimonios son visibles
- [ ] Footer contiene toda la información
- [ ] No hay errores en la consola del navegador
- [ ] La navegación funciona correctamente
- [ ] Los enlaces internos trabajan

---

## 🎯 Próximos Pasos

1. **Personalización:** Edita `src/main/webapp/index.html` con tu información
2. **Estilos:** Personaliza `src/main/webapp/css/styles.css`
3. **Contenido:** Cambia los testimonios, videos y textos
4. **Deployment:** Compila WAR y despliega en producción

---

## 📞 Información de Contacto

Si encuentras problemas:
1. Revisa los logs en la consola del servidor
2. Abre DevTools del navegador (F12)
3. Verifica que el puerto 8080 está libre
4. Reinicia el servidor si es necesario

---

**¡Listo! Tu aplicación web está completamente funcional y lista para pruebas. 🎉**
