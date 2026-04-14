# 🧪 Guía de Pruebas - Sistema de Correos

## 📝 Prueba 1: Verificar que el servidor inicia correctamente

### Pasos:
1. Abre PowerShell en la carpeta del proyecto
2. Ejecuta: `.\gradlew.bat runServer`

### ✅ Resultado esperado:
```
[INFO] Iniciando servidor Jetty...
[INFO] Servidor escuchando en puerto 8080
[INFO] Accede a: http://localhost:8080/rolfing
```

### ❌ Si falla:
- Verifica que tienes Java 11+: `java -version`
- El puerto 8080 ya está en uso: cambia `web.port` en `config.properties`

---

## 📋 Prueba 2: Verificar que la página carga

### Pasos:
1. Con el servidor corriendo, abre tu navegador
2. Ve a: `http://localhost:8080/rolfing`

### ✅ Resultado esperado:
- La página de inicio de Rolfing se carga correctamente
- Ves el formulario de contacto con campos: Nombre, Email, Teléfono, Asunto, Mensaje

### ❌ Si falla:
- Abre la consola del navegador (F12)
- Revisa los errores mostrados
- Verifica que el servidor está corriendo

---

## 📧 Prueba 3: Enviar formulario SIN configurar email

### Pasos:
1. Asegúrate que `email.enabled=false` en `config.properties`
2. Completa el formulario:
   - **Nombre**: "Test User"
   - **Email**: "test@example.com"
   - **Teléfono**: "+1 234 5678"
   - **Asunto**: "Prueba del formulario"
   - **Mensaje**: "Este es un mensaje de prueba"
3. Haz clic en "Enviar"

### ✅ Resultado esperado:
- Mensaje de éxito: "¡Mensaje enviado exitosamente!"
- En la consola del servidor ves:
  ```
  Nuevo contacto recibido:
    Nombre: Test User
    Email: test@example.com
    Teléfono: +1 234 5678
    Asunto: Prueba del formulario
    Mensaje: Este es un mensaje de prueba
  ```

### ❌ Si falla:
- Abre F12 → Network → busca la llamada a `/api/contact`
- Verifica que responde con status 200
- Revisa la consola del servidor para errores

---

## 🔧 Prueba 4: Configurar Gmail para pruebas

### Paso 1: Generar contraseña de aplicación
1. Abre: https://myaccount.google.com/apppasswords
2. Selecciona:
   - **App**: "Mail"
   - **Device**: "Windows Computer"
3. Google te mostrará una contraseña de 16 caracteres
4. Cópiala (sin espacios)

### Paso 2: Actualizar config.properties
```properties
email.smtp.host=smtp.gmail.com
email.smtp.port=587
email.smtp.user=TU-EMAIL@gmail.com
email.smtp.password=xxxx xxxx xxxx xxxx
email.enabled=true
contact.email=TU-EMAIL@gmail.com
```

### Paso 3: Reiniciar servidor
- Presiona `Ctrl+C` en la terminal
- Ejecuta nuevamente: `.\gradlew.bat runServer`

---

## 📨 Prueba 5: Enviar formulario CON email configurado

### Pasos:
1. Completa el formulario:
   - **Nombre**: "Juan García"
   - **Email**: "juan@example.com"
   - **Teléfono**: "+34 123 456 789"
   - **Asunto**: "Solicitud de cita"
   - **Mensaje**: "Deseo agendar una sesión de Rolfing para esta semana"
2. Haz clic en "Enviar"

### ✅ Resultado esperado - En el Navegador:
- Ves: "¡Mensaje enviado exitosamente! Nos pondremos en contacto pronto."
- El botón dice "Enviando..." durante 2-3 segundos, luego vuelve a "Enviar"

### ✅ Resultado esperado - En tu Bandeja de Entrada:
**Correo 1** (al administrador):
```
De: tu-email@gmail.com
Para: tu-email@gmail.com
Asunto: Nuevo contacto: Solicitud de cita

Nombre: Juan García
Email: juan@example.com
Teléfono: +34 123 456 789
Asunto: Solicitud de cita

Mensaje:
Deseo agendar una sesión de Rolfing para esta semana
```

**Correo 2** (al usuario):
```
De: tu-email@gmail.com
Para: juan@example.com
Asunto: Confirmación de mensaje recibido

Hola Juan García,

Hemos recibido tu mensaje correctamente. Uno de nuestros 
profesionales se pondrá en contacto contigo pronto.

Esperamos poder ayudarte.

Saludos,
El equipo de Rolfing
```

### ✅ En la consola del servidor:
```
Nuevo contacto recibido:
  Nombre: Juan García
  Email: juan@example.com
  Teléfono: +34 123 456 789
  Asunto: Solicitud de cita
  Mensaje: Deseo agendar una sesión de Rolfing para esta semana
Email enviado exitosamente a: tu-email@gmail.com
Email enviado exitosamente a: juan@example.com
```

### ❌ Si NO recibes los correos:
- Revisa carpeta de SPAM
- Verifica que `email.enabled=true`
- Verifica las credenciales SMTP en `config.properties`
- Revisa los logs en la consola (errores de autenticación, timeout, etc.)

---

## 🔍 Prueba 6: Validación de campos

### Prueba 6a: Campo nombre vacío
**Pasos**: Deja nombre vacío, completa otros y envía

**Esperado**: "Por favor completa todos los campos requeridos."

### Prueba 6b: Email inválido
**Pasos**: Ingresa "notanemail", completa otros y envía

**Esperado**: "Por favor ingresa un email válido."

### Prueba 6c: Email válido
**Pasos**: Ingresa "test@domain.com", completa otros y envía

**Esperado**: El formulario procesa correctamente

---

## 🛡️ Prueba 7: Seguridad - Inyección HTML

### Pasos:
1. En el campo Nombre, ingresa: `<script>alert('XSS')</script>`
2. Completa otros campos normalmente
3. Envía el formulario
4. Revisa el correo que recibas

### ✅ Resultado esperado:
- No hay popup de alert (XSS bloqueado)
- En el correo ves: `&lt;script&gt;alert('XSS')&lt;/script&gt;` (HTML escapado)
- El mensaje fue procesado seguramente

---

## ⚡ Prueba 8: Rendimiento - Múltiples envíos

### Pasos:
1. Envía 5 formularios seguidos con datos diferentes
2. Verifica que todos se procesan correctamente
3. Revisa que recibes 10 correos (5 al admin + 5 de confirmación)

### ✅ Resultado esperado:
- Todos los formularios responden con éxito
- Los correos llegan correctamente
- No hay errores en los logs

---

## 🔐 Prueba 9: Verificar que las credenciales NO están en el código

### Pasos:
1. Abre: `src/main/java/com/rolfing/service/EmailService.java`
2. Busca la palabra: "password" (Ctrl+F)
3. Busca tu contraseña (Ctrl+F)

### ✅ Resultado esperado:
- La contraseña NO aparece en el código
- Solo aparece en `config.properties`
- El código carga las credenciales desde un archivo externo

---

## 📊 Prueba 10: Monitoreo en tiempo real

### Pasos:
1. Mantén visible la consola del servidor
2. Envía un formulario
3. Observa los logs

### ✅ Resultado esperado:
Ves algo similar a:
```
Nuevo contacto recibido:
  Nombre: [nombre]
  Email: [email]
  Teléfono: [teléfono]
  Asunto: [asunto]
  Mensaje: [mensaje]
Email enviado exitosamente a: admin@email.com
Email enviado exitosamente a: user@email.com
```

---

## 📋 Checklist de Validación Completa

Antes de considerar que el sistema está funcionando al 100%, verifica:

- [ ] **Servidor inicia sin errores**
- [ ] **Página carga correctamente** en `http://localhost:8080/rolfing`
- [ ] **Formulario valida campos vacíos**
- [ ] **Formulario valida emails inválidos**
- [ ] **Con `email.enabled=false`, se registran en logs**
- [ ] **Gmail configurado correctamente** (credenciales válidas)
- [ ] **Recibes correo al administrador** cuando envías formulario
- [ ] **Recibes correo de confirmación** en el email que ingresaste
- [ ] **Los correos contienen HTML formateado** (no plain text)
- [ ] **La seguridad funciona** (HTML se escapa correctamente)
- [ ] **Las credenciales NO están en el código** (están en config.properties)
- [ ] **Múltiples envíos funcionan sin problemas**
- [ ] **Los logs muestran información útil** para debugging

---

## 🎯 Estados de Éxito

### ✅ FUNCIONA CORRECTAMENTE si:
1. El formulario se envía sin errores
2. Recibes 2 correos por cada envío
3. Los datos aparecen en los logs
4. El navegador muestra mensaje de éxito
5. Las validaciones funcionan como esperado

### ⚠️ FUNCIONA CON ADVERTENCIA si:
- `email.enabled=false` (funciona pero sin envío real)
- Gmail rechaza credenciales (verifica contraseña de aplicación)
- El servidor tarda mucho en enviar (verifica conexión SMTP)

### ❌ NO FUNCIONA si:
- El servidor no inicia (revisa Java, puerto)
- La página no carga (revisa logs del servidor)
- El formulario no se envía (revisa consola F12)
- No llegas a ver validaciones (revisa script.js)
- No recibes correos (revisa config.properties, SMTP)

---

## 🚀 Resultado Final

Cuando completes TODAS las pruebas exitosamente, el sistema está **100% funcional** y listo para producción.

**Nota**: Los últimos pasos (seguridad, rendimiento, monitoreo) son para validar la calidad del sistema. Las 5 primeras pruebas son **críticas**.

---

## 📞 Soporte

Si algo no funciona:
1. Revisa los logs en la consola del servidor
2. Abre F12 en el navegador (Network tab)
3. Verifica el archivo `config.properties`
4. Consulta `EMAIL_SETUP.md` para problemas específicos de SMTP

---

**¡Éxito en las pruebas!** 🎉
