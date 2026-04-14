# ✅ Setup Final: Mailjet para Render (GRATIS)

El código ya está implementado. Mailjet es **completamente gratis, sin expiración** y funciona perfectamente en Render.

---

## 💰 ¿Por qué Mailjet?

| Feature | Mailjet | SendGrid |
|---------|---------|----------|
| Precio | **GRATIS SIEMPRE** | Gratis 30 días, luego pago |
| Límite | 200 emails/día | 100 emails/día |
| Expiración | Nunca | No |
| Tarjeta Crédito | NO necesaria | Necesaria (después) |
| API REST | ✅ SÍ | ✅ SÍ |

---

## 📋 Paso 1: Crear Cuenta en Mailjet (2 minutos)

1. Ve a https://www.mailjet.com/
2. Haz click en **Sign up** (arriba a la derecha)
3. Selecciona **Free Plan** (el primer opción)
4. Completa:
   - **Email:** tu correo
   - **Password:** contraseña segura
   - Acepta términos
5. Click **Sign up**
6. **Verifica tu email** (revisa spam si no llega)

---

## 🔑 Paso 2: Obtener API Keys de Mailjet (3 minutos)

1. **Login** en Mailjet (https://app.mailjet.com/)
2. En el menú izquierdo, ve a **Settings** (engranaje) → **API Management**
3. Verás dos claves:
   - **API Key** (clave pública)
   - **API Secret Key** (clave privada)
4. **Copia ambas** y guárdalas en un lugar seguro

Ejemplo de lo que copiarás:
```
API Key:    abc123def456ghi789...
API Secret: xyz789uvw456rst123...
```

---

## 🚀 Paso 3: Agregar API Keys en Render Dashboard (2 minutos)

1. Abre **Render Dashboard** (https://dashboard.render.com/)
2. Selecciona tu servicio `rolfing-web`
3. Ve a **Environment** (en el menú superior)
4. Agrega **DOS** nuevas variables:

### Primera Variable:
- **Key:** `MAILJET_API_KEY`
- **Value:** `abc123def456ghi789...` (tu API Key)
- Click **Save**

### Segunda Variable:
- **Key:** `MAILJET_API_SECRET`
- **Value:** `xyz789uvw456rst123...` (tu API Secret)
- Click **Save**

5. **Espera a que redeploy termine** (verás "Deploy in progress" → "Live")

---

## ✨ ¡Listo!

Ahora:

1. Abre tu sitio en Render: https://rolfing-web.onrender.com/
2. Ve al formulario de contacto
3. Completa y envía un mensaje
4. **Deberías recibir:**
   - Email al admin (`jmmartinezastroza@gmail.com`)
   - Email de confirmación al usuario (el email del formulario)

En los logs de Render verás:
```
✅ CORREO ENVIADO exitosamente via Mailjet
```

---

## 🔍 Si Algo Falla

### No recibes los emails:

1. **Verifica los logs de Render:**
   - Render Dashboard → tu servicio → **Logs**
   - Envía el formulario
   - Busca `MAILJET` en los logs
   - Si ves `✅ CORREO ENVIADO`, Mailjet funcionó ✅
   - Si ves error, cópialo y cuéntame

2. **Verifica las API Keys:**
   - Abre Mailjet → Settings → API Management
   - Cópialas nuevamente en Render si es necesario

3. **Verifica la bandeja de spam:**
   - A veces los emails caen en spam
   - Busca emails de `noreply@rolfing.com` o similar

### El error dice "Mailjet no está configurado":

- Faltan una o ambas variables en Render Environment
- Abre Render → Environment y verifica que tengas:
  - `MAILJET_API_KEY`
  - `MAILJET_API_SECRET`

---

## 📊 Límites de Mailjet Plan Free

- **200 emails/día** (suficiente para cualquier sitio pequeño/mediano)
- **COMPLETAMENTE GRATIS** (nunca pide tarjeta de crédito)
- Sin expiración (válido para siempre)

---

## 🎯 Resumen Rápido

| Paso | Acción | Tiempo |
|------|--------|--------|
| 1 | Crear cuenta Mailjet | 2 min |
| 2 | Obtener API Keys | 3 min |
| 3 | Agregar en Render | 2 min |
| **Total** | | **7 minutos** |

---

## 💡 Comparativa Final

- **Mailjet:** Gratis siempre, 200/día ✅ **RECOMENDADO**
- SendGrid: Gratis 30 días, 100/día, luego pago ❌
- Gmail SMTP: Gratis pero bloqueado en Render ❌

---

**¡Eso es todo!** El código ya está implementado. Solo necesitas las 2 API Keys. 🎉
