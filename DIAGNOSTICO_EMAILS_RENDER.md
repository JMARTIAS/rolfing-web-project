# 🔍 DIAGNÓSTICO: Los Correos No Llegan en Render

## 1️⃣ Verificar los Logs de Render

**Este es el paso más importante:**

1. Ve a https://dashboard.render.com/
2. Selecciona tu servicio `rolfing-web`
3. Abre la pestaña **Logs**
4. Envía el formulario de contacto desde tu sitio
5. **Busca en los logs estas palabras clave:**
   - `CONFIGURACIÓN DE EMAIL CARGADA`
   - `ERROR DE AUTENTICACIÓN`
   - `ERROR AL ENVIAR`
   - `Connection refused`
   - `connect timed out`

## 2️⃣ Posibles Problemas y Soluciones

### ❌ Problema: "Usuario SMTP no está configurado correctamente"
**Causa:** La variable `MAIL_SMTP_USER` no llega correctamente desde Render

**Solución:**
1. En Render Dashboard → Environment
2. Verifica que `MAIL_SMTP_USER=jmmartinezastroza@gmail.com` esté exactamente así
3. Sin espacios al inicio/final
4. Click en "Save"
5. Espera a que el servicio redesplegue (verás "Deploy in progress")

---

### ❌ Problema: "Contraseña SMTP no está configurada correctamente"
**Causa:** La variable `MAIL_SMTP_PASSWORD` no llega desde Render o está mal ingresada

**Solución:**
1. En Render Dashboard → Environment
2. **CUIDADO:** Copiar contraseña correctamente:
   - **Debe ser:** `reff bcpc rvio yoss` (con espacios entre los grupos)
   - NO está el archivo `.env` en el proyecto
   - NO está en `config.properties` en Render (lee las variables de entorno)
3. Paste la contraseña exactamente como está
4. Click "Save"

---

### ❌ Problema: "Connection refused" o "connect timed out"
**Causa:** Render tiene firewall o restricciones en el puerto 587

**Solución alternativa - Usar puerto 465 (SSL) en lugar de 587:**

En Render Dashboard → Environment, cambia a:
```
MAIL_SMTP_PORT=465
```

Luego, en el código cambiar propiedades SMTP:
- Cambiar `mail.smtp.starttls.enable=true` a `false`
- Agregar `mail.smtp.socketFactory.port=465`
- Agregar `mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory`

---

### ❌ Problema: "ERROR DE AUTENTICACIÓN SMTP"
**Causa:** Credenciales incorrectas o Google tiene bloqueada la app

**Solución:**
1. Abre https://myaccount.google.com/apppasswords
2. Inicia sesión en tu cuenta `jmmartinezastroza@gmail.com`
3. Selecciona:
   - App: **Correo**
   - Dispositivo: **Windows**
4. Google te generará una contraseña de 16 caracteres (ej: `xxxx xxxx xxxx xxxx`)
5. **COPIA la contraseña completa** (con espacios)
6. En Render → Environment → `MAIL_SMTP_PASSWORD` = pega la nueva contraseña
7. Click "Save"

---

## 3️⃣ Verificación Paso a Paso

### Checklist:

- [ ] Abriste los logs de Render (panel derecho después de enviar formulario)
- [ ] Viste "CONFIGURACIÓN DE EMAIL CARGADA"
- [ ] Viste `Habilitado: ✅ Habilitado`
- [ ] Viste `Usuario SMTP: jmm***@gmail.com` (mascarado)
- [ ] Viste `Contraseña config.: ✅ SÍ`
- [ ] Viste `DEBUG: MAIL_SMTP_USER env: ✅ SET`
- [ ] Viste `DEBUG: MAIL_SMTP_PASSWORD env: ✅ SET`
- [ ] Viste `✅ ÉXITO: Correo enviado` (si todo está correcto)

---

## 4️⃣ Si Aún No Funciona

**Proporciona en tu próxima respuesta:**

1. El contenido COMPLETO de los logs de Render (después de enviar el formulario)
2. Una captura de pantalla de las variables de entorno en Render Dashboard
3. Confirmación de que:
   - ¿Agregaste las variables en Render?
   - ¿Esperaste a que el servicio redesplegara (status = "Live")?
   - ¿Recargaste la página web en Render después del redeploy?

---

## 5️⃣ Información Local vs Render

**En Local (funciona):**
- Lee credenciales de `config.properties` con valores hardcodeados
- Red local, sin latencia
- Timeouts de 5s son suficientes

**En Render (no funciona):**
- Lee credenciales de variables de entorno (que deben estar en Dashboard)
- Red remota, más latencia
- Timeouts aumentados a 15s
- Puede haber firewall bloqueando puerto 587

---

## 6️⃣ Alternativa: Usar un servicio de email dedicado

Si después de todo esto sigue sin funcionar, considera:
- **SendGrid** (Render tiene soporte nativo)
- **Mailgun**
- **AWS SES**

Pero primero intenta con los pasos de arriba.
