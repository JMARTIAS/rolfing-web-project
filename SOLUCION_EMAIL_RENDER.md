# 📧 Solución: Email No Funciona en Render

## El Problema

Render en plan **free** bloquea conexiones SMTP salientes (puertos 587 y 465). El formulario muestra:

```
❌ Error: Couldn't connect to host, port: smtp.gmail.com, 587; timeout 20000
```

## ✅ Soluciones (Elige una)

### Opción 1: Usar SendGrid (RECOMENDADO - Sin código adicional)

SendGrid es un servicio de email que funciona via HTTP (no SMTP) y tiene soporte nativo en Render.

**Pasos:**

1. **Crea cuenta gratuita en SendGrid:**
   - Ve a https://sendgrid.com
   - Haz click en "Sign Up Free"
   - Completa tu email y contraseña
   - Verifica tu correo

2. **Obtén tu API Key:**
   - Una vez dentro, ve a **Settings** → **API Keys**
   - Click en **Create API Key**
   - Dale nombre: `Rolfing`
   - Selecciona permisos: **Mail Send** (es suficiente)
   - Copia la API Key (formato: `SG.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`)
   - ⚠️ **GUARDA ESTA CLAVE EN UN LUGAR SEGURO** - No la pierdes de vista

3. **Agrega a Render Dashboard:**
   - Ve a tu servicio `rolfing-web` en Render
   - **Settings** → **Environment**
   - Agrega nueva variable:
     - **Key:** `SENDGRID_API_KEY`
     - **Value:** `SG.xxxxxxxxxx` (la que copiaste arriba)
   - Click en **Save**
   - Render redesplegará automáticamente

4. **Listo:** El formulario ahora enviará emails via SendGrid

---

### Opción 2: Usar Plan Pagado de Render

Cambiar de **Free** a **Paid** ($7/mes) para obtener conexiones SMTP salientes.

- ❌ Es caro por solo uno o dos features
- ✅ Funciona con Gmail SMTP sin cambios de código

---

### Opción 3: Usar un Servidor VPS diferente

Si prefieres no usar Render, usa:
- **Railway.app** (tiene SMTP saliente en free)
- **Heroku** (SMTP via SendGrid/MailGun)
- **AWS/Digital Ocean** (SMTP abierto)

---

## 🔍 Verificar que Funciona

Después de agregar `SENDGRID_API_KEY` en Render:

1. Espera a que redeploy termine (status = "Live")
2. Recarga tu sitio
3. Envía el formulario
4. Deberías recibir los emails

**Si falla:** Revisa los logs de Render → verás el error exacto

---

## ℹ️ Comparativa

| Método | Ventajas | Desventajas |
|--------|----------|-------------|
| **Gmail SMTP** | Gratis, sin dependencias | Bloquea Render free, timeout |
| **SendGrid** | HTTP, no SMTP, gratis hasta 100 emails/día | Requiere registrarse en otro servicio |
| **Render Paid** | Funciona SMTP nativo | Cuesta $7/mes |

---

## 💡 Mi Recomendación

**Usa SendGrid** porque:
- Es gratis para principiantes (100 emails/día)
- No requiere cambios de código (si agregas SENDGRID_API_KEY)
- Funciona perfectamente en Render free
- Es confiable y profesional

---

## Próximos Pasos

1. Elige una solución arriba
2. Si eliges SendGrid, ejecuta los 4 pasos
3. Prueba el formulario
4. Si sigue sin funcionar, abre los **Logs de Render** y comparte el error exacto
