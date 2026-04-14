# 🧪 Prueba de Email Local - Verificar Que Todo Funciona

## Paso 1: Compilar y ejecutar localmente

```bash
# Limpiar y compilar
./gradlew clean build

# Ejecutar la aplicación
java -cp build/libs/rolfing.war com.rolfing.server.JettyServer
```

## Paso 2: Enviar formulario de prueba

1. Abre http://localhost:8080/rolfing
2. Ve al formulario de contacto
3. Rellena:
   - **Nombre:** Test User
   - **Email:** tu-email@gmail.com (donde quieras recibir el correo de confirmación)
   - **Teléfono:** +1234567890
   - **Asunto:** Prueba Local
   - **Mensaje:** Este es un mensaje de prueba desde local
4. Click en "Enviar"

## Paso 3: Revisar los logs locales

En la consola donde ejecutaste el servidor, busca:

```
╔════════════════════════════════════════════════════════╗
║          CONFIGURACIÓN DE EMAIL CARGADA                ║
╠════════════════════════════════════════════════════════╣
║ Habilitado:          ✅ Habilitado
║ Host SMTP:           smtp.gmail.com
║ Puerto SMTP:         587
║ Usuario SMTP:        jmm***@gmail.com
║ Contraseña config.:  ✅ SÍ
╚════════════════════════════════════════════════════════╝

--- INTENTO DE ENVÍO DE CORREO ---
Para: jmmartinezastroza@gmail.com
Asunto: Nuevo contacto: Prueba Local
✅ ÉXITO: Correo enviado a jmmartinezastroza@gmail.com
```

## Paso 4: Verificar los emails

- ✅ Verifica tu bandeja de entrada de jmmartinezastroza@gmail.com
- ✅ Verifica el email de confirmación en tu-email@gmail.com

## Si falla localmente también:

Entonces el problema es:
1. Contraseña de aplicación incorrecta
2. Cuenta de Google no tiene 2FA habilitado
3. La app de Gmail no tiene permisos

### Solución:
1. Ve a https://myaccount.google.com/apppasswords
2. Asegúrate de haber seleccionado:
   - App: **Correo**
   - Dispositivo: **Windows** o **Otro (escribe: Rolfing)**
3. Genera nueva contraseña
4. Actualiza en `config.properties`:
   ```properties
   email.smtp.password=xxxx xxxx xxxx xxxx
   ```
5. Vuelve a compilar y prueba

## Si funciona localmente pero NO en Render:

Entonces el problema es específicamente la configuración en Render:
1. Verifica que las variables de entorno estén en Render Dashboard
2. Espera a que el servicio redesplegue completamente
3. Revisa los logs de Render (no locales)
