# 🔐 Configuración de Mailjet en Render - Pasos Exactos

## ⚠️ IMPORTANTE - Seguridad

**NUNCA** guardes las API Keys en el código (GitHub, etc.). Solo en Render Environment.

---

## 🎯 Tus Credenciales Mailjet

Has proporcionado:
- **API Key:** `a0bc159e9e21b556e9a5fb6e4bb8323a`
- **Secret Key:** `1f6bca54184bd85459148b9f9a9e5825`

---

## 📋 Pasos para Agregar en Render Dashboard

### 1. Abre Render Dashboard
- Ve a https://dashboard.render.com/
- Selecciona tu servicio **`rolfing-web`**

### 2. Abre la Sección Environment
- En el menú superior, haz click en **Environment**
- O ve a **Settings** → **Environment Variables**

### 3. Agrega Primera Variable (API Key)
1. Click en **Add Environment Variable**
2. Completa:
   - **Key:** `MAILJET_API_KEY`
   - **Value:** `a0bc159e9e21b556e9a5fb6e4bb8323a`
3. Click **Save**

### 4. Agrega Segunda Variable (Secret Key)
1. Click en **Add Environment Variable**
2. Completa:
   - **Key:** `MAILJET_API_SECRET`
   - **Value:** `1f6bca54184bd85459148b9f9a9e5825`
3. Click **Save**

### 5. Espera a que Redeploy Termine
- Verás un estado que dice "Deploy in progress"
- Espera a que termine (unos 2-3 minutos)
- Cuando diga **"Live"**, está listo ✅

---

## ✅ Verificación

Después de que Render redepliegue:

1. Abre tu sitio: https://rolfing-web.onrender.com/
2. Ve al formulario de contacto
3. Completa y envía un mensaje de prueba
4. Deberías recibir 2 emails:
   - Email al admin (`jmmartinezastroza@gmail.com`)
   - Email de confirmación al usuario (tu email)

---

## 🔍 Ver los Logs

Si quieres ver que está funcionando:

1. Ve a Render Dashboard → tu servicio → **Logs**
2. Envía el formulario desde tu sitio
3. Busca `MAILJET` en los logs
4. Deberías ver: `✅ CORREO ENVIADO exitosamente via Mailjet`

---

## ❌ Si Falla

**Error común:** "Mailjet no está configurado"
- Las variables no llegaron a Render
- Verifica que ambas estén en **Environment**
- Recopia la API Key y Secret Key

**Error de autenticación:**
- Las keys son incorrectas
- Vuelve a copiarlas de Mailjet: Settings → API Management
- Agrégalas nuevamente en Render

---

## 🎉 ¡Listo!

Una vez hecho, el formulario de contacto funcionará automáticamente sin que tengas que hacer nada más.

El sistema intentará usar **Mailjet primero** (gratis, sin expiración), y si falla, caerá a SMTP.

**Necesitas ayuda con los pasos? Dime!** 📞
