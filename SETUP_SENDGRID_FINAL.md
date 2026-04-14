# ✅ Setup Final: SendGrid para Render

El código ya está listo. Solo necesitas **3 pasos simples** para que el email funcione.

---

## 📋 Paso 1: Crear Cuenta en SendGrid (2 minutos)

1. Ve a https://sendgrid.com/
2. Haz click en **Sign Up Free**
3. Completa:
   - **Email:** tu email
   - **Password:** una contraseña segura
4. Click **Create Account**
5. **Verifica tu email** (revisa spam si no llega)

---

## 🔑 Paso 2: Obtener API Key de SendGrid (3 minutos)

1. **Login** en SendGrid (https://app.sendgrid.com/)
2. Desde el menú superior, ve a **Settings**
3. Click en **API Keys** (en el menú lateral izquierdo)
4. Click en **Create API Key** (botón azul arriba a la derecha)
5. En el formulario:
   - **API Key Name:** `Rolfing` (o lo que quieras)
   - **Permissions:** Selecciona **Full Access** (para simplificar)
   - Click **Create & Copy** (te copiará automáticamente la clave)
6. **COPIA Y GUARDA la API Key en un lugar seguro**
   - Ejemplo: `SG.abc123defgh...`
   - ⚠️ No la pierda, SendGrid no la vuelve a mostrar después

---

## 🚀 Paso 3: Agregar API Key en Render Dashboard (2 minutos)

1. Abre **Render Dashboard** (https://dashboard.render.com/)
2. Selecciona tu servicio `rolfing-web`
3. Ve a **Environment** (en el menú superior)
4. Click en **Add Environment Variable**
5. Completa:
   - **Key:** `SENDGRID_API_KEY`
   - **Value:** `SG.abc123defgh...` (pega tu API Key aquí)
6. Click **Save**
7. **Espera a que redeploy termine** (verás "Deploy in progress" → "Live")

---

## ✨ Listo! 

Ahora:

1. Abre tu sitio en Render: https://rolfing-web.onrender.com/
2. Ve al formulario de contacto
3. Completa y envía un mensaje
4. **Deberías recibir:**
   - Email al admin (`jmmartinezastroza@gmail.com`)
   - Email de confirmación al usuario (el email del formulario)

---

## 🔍 Si Algo Falla

### No recibes los emails:

1. **Verifica los logs de Render:**
   - Ve a Render Dashboard → tu servicio → **Logs**
   - Envía el formulario
   - Busca `SENDGRID` en los logs
   - Si ves `✅ CORREO ENVIADO`, SendGrid funcionó
   - Si ves error, cópialo y cuéntame

2. **Verifica la API Key:**
   - Abre SendGrid → Settings → API Keys
   - Confirma que la clave está activa (no expirada)
   - Cópiala nuevamente en Render si es necesario

3. **Verifica la bandeja de spam:**
   - A veces los emails caen en spam
   - Busca emails de `noreply@rolfing.com` o similar

### El error dice "SENDGRID_API_KEY no está configurado":

- La variable no está en Render Environment
- Ve a Render → Settings → Environment
- Verifica que `SENDGRID_API_KEY` esté presente
- Si no está, créala manualmente

---

## 📊 Límites de SendGrid Plan Free

- **100 emails/día** (suficiente para un sitio pequeño)
- **Siempre gratis** (no requiere tarjeta de crédito)
- Después puedes pasar a plan pagado si necesitas más

---

## 🎯 Resumen Rápido

| Paso | Acción | Tiempo |
|------|--------|--------|
| 1 | Crear cuenta SendGrid | 2 min |
| 2 | Obtener API Key | 3 min |
| 3 | Agregar en Render | 2 min |
| **Total** | | **7 minutos** |

---

**¡Eso es todo!** El código ya está implementado. Solo necesitas la API Key. 🎉
