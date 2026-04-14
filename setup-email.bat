@echo off
REM Script para configurar credenciales de email de manera fácil
REM Solo para Windows

echo.
echo ============================================
echo   CONFIGURACIÓN DE EMAIL - ROLFING PROJECT
echo ============================================
echo.
echo Este script te ayudará a configurar el envío de correos.
echo.

setlocal enabledelayedexpansion

REM Intenta actualizar el archivo config.properties
set "CONFIG_FILE=src\main\resources\config.properties"

if not exist "%CONFIG_FILE%" (
    echo Error: No se encontró %CONFIG_FILE%
    pause
    exit /b 1
)

echo.
echo ¿De dónde deseas configurar el email?
echo 1. Variables de Entorno (RECOMENDADO)
echo 2. Archivo config.properties
echo.

set /p OPCION="Selecciona opción (1 o 2): "

if "%OPCION%"=="1" (
    echo.
    echo ============================================
    echo OPCIÓN SELECCIONADA: Variables de Entorno
    echo ============================================
    echo.
    echo Ingresa tus credenciales de Gmail:
    echo.
    
    set /p EMAIL="Email de Gmail: "
    set /p PASSWORD="Contraseña de Aplicación (16 caracteres): "
    set /p ADMIN_EMAIL="Email donde recibir contactos: "
    
    echo.
    echo Configurando variables de entorno...
    echo.
    
    setx MAIL_SMTP_HOST smtp.gmail.com
    setx MAIL_SMTP_PORT 587
    setx MAIL_SMTP_USER %EMAIL%
    setx MAIL_SMTP_PASSWORD %PASSWORD%
    setx BUSINESS_EMAIL %ADMIN_EMAIL%
    
    echo.
    echo ✅ Variables de entorno configuradas exitosamente
    echo.
    echo IMPORTANTE: Debes REINICIAR tu terminal/IDE para que los cambios tomen efecto
    echo.
    
) else if "%OPCION%"=="2" (
    echo.
    echo ============================================
    echo OPCIÓN SELECCIONADA: config.properties
    echo ============================================
    echo.
    echo Abre con un editor de texto:
    echo %CONFIG_FILE%
    echo.
    echo Y reemplaza estas líneas:
    echo   email.smtp.user=
    echo   email.smtp.password=
    echo   contact.email=
    echo.
    echo Con tus datos:
    echo   email.smtp.user=tu-email@gmail.com
    echo   email.smtp.password=xxxx xxxx xxxx xxxx
    echo   contact.email=tu-email@gmail.com
    echo.
    echo Abre el archivo ahora? (S/N)
    set /p OPEN="Respuesta: "
    
    if /i "%OPEN%"=="S" (
        start notepad "%CONFIG_FILE%"
    )
) else (
    echo Opción no válida
    pause
    exit /b 1
)

echo.
echo Despues de configurar, ejecuta:
echo   .\gradlew.bat build
echo   .\gradlew.bat runServer
echo.
echo Luego prueba el formulario en: http://localhost:8080/rolfing
echo.
pause
