# Configuración del Envío de Correos Electrónicos

## 📧 Descripción General

Se ha implementado un sistema completo de envío de correos electrónicos para el formulario de contacto. Los correos se envían a través de SMTP.

## 🔧 Pasos para Habilitar el Envío de Correos

### 1. **Edita el archivo `config.properties`**

Abre `config.properties` en la raíz del proyecto y busca la sección `CONFIGURACIÓN DE EMAIL`:

```properties
email.smtp.host=smtp.gmail.com
email.smtp.port=587
email.smtp.user=tu-email@gmail.com
email.smtp.password=tu-contraseña
email.enabled=true
```

### 2. **Configura tu servidor SMTP**

#### Opción A: Usar Gmail (Recomendado)

1. **Habilita la verificación en dos pasos** en tu cuenta de Google:
   - Ve a [Google Account Security](https://myaccount.google.com/security)

2. **Genera una contraseña de aplicación**:
   - Ve a [App Passwords](https://myaccount.google.com/apppasswords)
   - Selecciona "Mail" y "Windows Computer"
   - Google generará una contraseña de 16 caracteres

3. **Actualiza `config.properties`**:
   ```properties
   email.smtp.host=smtp.gmail.com
   email.smtp.port=587
   email.smtp.user=tu-email@gmail.com
   email.smtp.password=xxxx xxxx xxxx xxxx  # Tu contraseña de aplicación
   email.enabled=true
   ```

#### Opción B: Usar Outlook/Hotmail

```properties
email.smtp.host=smtp-mail.outlook.com
email.smtp.port=587
email.smtp.user=tu-email@outlook.com
email.smtp.password=tu-contraseña
email.enabled=true
```

#### Opción C: Usar un servidor SMTP personalizado

Contacta a tu proveedor de hosting para obtener:
- `smtp.host` (servidor SMTP)
- `smtp.port` (puerto, generalmente 587 o 465)
- `smtp.user` (usuario/email)
- `smtp.password` (contraseña)

### 3. **Verifica que el email del administrador está configurado**

En `config.properties`, busca:
```properties
contact.email=info@rolfing.com
```

Este es el email donde se recibirán los mensajes de contacto. Cámbialo a tu email.

### 4. **Compila el proyecto**

```bash
# Si usas Gradle
gradle build

# O con el wrapper
./gradlew build
```

### 5. **Ejecuta el servidor**

```bash
# Opción 1: Con Gradle
gradle runServer

# Opción 2: Con el JAR generado
java -jar build/libs/rolfing-app.jar

# Opción 3: Con Maven (si lo tienes configurado)
mvn jetty:run
```

### 6. **Prueba el formulario de contacto**

- Abre el navegador y ve a `http://localhost:8080/rolfing`
- Completa el formulario de contacto con tus datos
- Envía el formulario
- Deberías recibir dos correos:
  1. **Correo al administrador**: Con el mensaje del usuario
  2. **Correo de confirmación al usuario**: Confirmando que su mensaje fue recibido

## ✉️ Flujo de Correos

### Cuando un usuario envía el formulario:

1. **Validación en Frontend** (JavaScript)
   - Se valida que los campos requeridos estén completos
   - Se valida que el email tenga formato válido

2. **Envío al Servidor** (Frontend → Backend)
   - Se hace un `fetch` POST a `/api/contact`
   - Se envían los datos: nombre, email, teléfono, asunto, mensaje

3. **Validación en Backend** (Java Servlet)
   - Se valida nuevamente en el servidor
   - Se sanitizan los datos para prevenir inyecciones

4. **Envío de Correos** (EmailService)
   - **Correo 1**: Enviado al administrador con los detalles del contacto
   - **Correo 2**: Enviado al usuario confirmando que recibimos su mensaje

5. **Respuesta al Cliente**
   - El cliente recibe una respuesta JSON con el estado

## 📋 Estructura de Archivos Nuevos/Modificados

### Nuevos:
- `src/main/java/com/rolfing/service/EmailService.java` - Servicio de envío de correos

### Modificados:
- `build.gradle.kts` - Se agregaron dependencias de JavaMail
- `src/main/java/com/rolfing/servlet/ContactServlet.java` - Se implementó el envío real de correos
- `src/main/webapp/js/script.js` - Se cambió la simulación por fetch real
- `config.properties` - Se agregaron variables de configuración SMTP

## 🔐 Seguridad

- Los correos se envían con TLS encriptado
- Las contraseñas NO se guardan en código, solo en el archivo `config.properties`
- Se validan y sanitizan todos los inputs
- Se valida formato de email antes de enviar

## 🐛 Solución de Problemas

### "Email no enviado" o "Nota: Los emails no pudieron ser enviados"

1. **Verifica que `email.enabled=true`** en `config.properties`
2. **Verifica las credenciales SMTP**:
   - ¿Es correctamente el usuario SMTP?
   - ¿Es correctamente la contraseña?
   - ¿Es correcto el host y puerto?
3. **Revisa los logs**:
   - Busca mensajes de error en la consola del servidor
   - Verifica el archivo `logs/rolfing.log` si existe

### "Método no permitido" o error 405

- Asegúrate de que estás usando POST, no GET
- El formulario debe usar `method="post"`

### Timeout o error de conexión

- Verifica tu conexión a internet
- Verifica que el servidor SMTP sea accesible (a veces hay firewalls corporativos)
- Intenta con un puerto diferente (465 para SSL, 587 para TLS)

## 📞 Contacto y Soporte

Para más información sobre configuración SMTP:
- [Gmail: Usar credenciales de aplicación](https://support.google.com/mail/answer/185833)
- [Outlook: Configuración SMTP](https://support.microsoft.com/es-es/office/configuracion-smtp)

## ✅ Checklist de Configuración

- [ ] Acceso a la cuenta de correo (Gmail, Outlook, etc.)
- [ ] Contraseña de aplicación generada (si es Gmail)
- [ ] `email.smtp.host` configurado correctamente
- [ ] `email.smtp.port` configurado correctamente
- [ ] `email.smtp.user` configurado correctamente
- [ ] `email.smtp.password` configurado correctamente
- [ ] `email.enabled=true`
- [ ] `contact.email` actualizado a tu email
- [ ] Proyecto compilado sin errores
- [ ] Servidor ejecutándose
- [ ] Formulario probado exitosamente

---

**Nota**: Si `email.enabled=false`, el formulario seguirá funcionando pero no enviará correos reales. Los datos se seguirán registrando en los logs del servidor.
