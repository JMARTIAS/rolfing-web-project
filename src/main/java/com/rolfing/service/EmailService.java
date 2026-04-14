package com.rolfing.service;

import com.rolfing.config.ConfigManager;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Servicio para enviar correos electrónicos.
 * Estrategia:
 * 1. Si SendGrid está configurado (SENDGRID_API_KEY), usarlo (HTTP, no SMTP)
 * 2. Sino, intentar SMTP (puerto 465 SSL, luego 587 STARTTLS)
 */
public class EmailService {

    private String smtpHost;
    private String smtpUser;
    private String smtpPassword;
    private boolean emailEnabled;
    private String lastError = "";
    private final ConfigManager config;
    private SendGridEmailService sendGridService;

    public EmailService() {
        this.config = ConfigManager.getInstance();
        this.sendGridService = new SendGridEmailService();
        loadConfiguration();
    }

    private void loadConfiguration() {
        this.smtpHost     = config.get("MAIL_SMTP_HOST",     "email.smtp.host",     "smtp.gmail.com");
        this.smtpUser     = config.get("MAIL_SMTP_USER",     "email.smtp.user",     "");
        this.smtpPassword = config.get("MAIL_SMTP_PASSWORD", "email.smtp.password", "");
        this.emailEnabled = config.getBoolean("email.enabled", true);

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   CONFIGURACIÓN DE EMAIL CARGADA       ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Habilitado: " + (emailEnabled ? "✅ SÍ" : "❌ NO"));
        System.out.println("║ SMTP Host:  " + smtpHost);
        System.out.println("║ Usuario:    " + maskEmail(smtpUser));
        System.out.println("║ Password:   " + (smtpPassword != null && !smtpPassword.isEmpty() ? "✅ configurada" : "❌ vacía"));
        System.out.println("║ Env USER:   " + (System.getenv("MAIL_SMTP_USER")     != null ? "✅ SET" : "❌ NOT SET"));
        System.out.println("║ Env PASS:   " + (System.getenv("MAIL_SMTP_PASSWORD") != null ? "✅ SET" : "❌ NOT SET"));
        System.out.println("╚════════════════════════════════════════╝\n");

        if (emailEnabled) {
            if (smtpUser == null || smtpUser.isEmpty() || smtpUser.contains("tu-email")) {
                System.err.println("⚠️  ADVERTENCIA: MAIL_SMTP_USER no configurado");
                this.emailEnabled = false;
            }
            if (smtpPassword == null || smtpPassword.isEmpty()) {
                System.err.println("⚠️  ADVERTENCIA: MAIL_SMTP_PASSWORD no configurado");
                this.emailEnabled = false;
            }
        }
    }

    /** Devuelve el último error ocurrido (útil para el servlet) */
    public String getLastError() {
        return lastError;
    }

    public String getLastError() {
        return lastError;
    }

    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("\n--- INTENTO DE ENVÍO ---");
        System.out.println("Para:   " + to);
        System.out.println("Asunto: " + subject);

        // Estrategia 1: Si SendGrid está configurado, usarlo (funciona en Render)
        if (sendGridService.isEnabled()) {
            System.out.println("📧 ESTRATEGIA: SendGrid (HTTP)");
            if (sendGridService.sendEmail(to, subject, body)) {
                lastError = "";
                return true;
            } else {
                lastError = sendGridService.getLastError();
                System.err.println("⚠️  SendGrid falló: " + lastError);
                return false;
            }
        }

        // Estrategia 2: Si no está SendGrid, intentar SMTP
        System.out.println("📧 ESTRATEGIA: SMTP (porta 465 y 587)");

        if (!emailEnabled) {
            lastError = "Email deshabilitado o sin credenciales";
            System.err.println("❌ " + lastError);
            return false;
        }

        // Intento 1: puerto 465 SSL
        System.out.println("🔌 Intento 1: Puerto 465 SSL...");
        if (trySend(to, subject, body, 465, true)) {
            return true;
        }
        System.err.println("⚠️  Puerto 465 falló: " + lastError);

        // Intento 2: puerto 587 STARTTLS
        System.out.println("🔌 Intento 2: Puerto 587 STARTTLS...");
        if (trySend(to, subject, body, 587, false)) {
            return true;
        }
        System.err.println("⚠️  Puerto 587 falló: " + lastError);

        System.err.println("❌ TODOS LOS MÉTODOS FALLARON");
        lastError = "No se pudo enviar por SMTP ni SendGrid. Configura SENDGRID_API_KEY en Render.";
        return false;
    }

    /**
     * Intenta enviar el correo con la configuración especificada.
     * @param useSSL true = SSL en puerto 465, false = STARTTLS en puerto 587
     */
    private boolean trySend(String to, String subject, String body, int port, boolean useSSL) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", String.valueOf(port));
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", "20000");
            props.put("mail.smtp.timeout",           "20000");
            props.put("mail.smtp.writetimeout",      "20000");

            if (useSSL) {
                // Puerto 465 – SSL/TLS directo
                props.put("mail.smtp.ssl.enable",          "true");
                props.put("mail.smtp.ssl.trust",           smtpHost);
                props.put("mail.smtp.ssl.protocols",       "TLSv1.2");
                props.put("mail.smtp.socketFactory.port",  String.valueOf(port));
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
            } else {
                // Puerto 587 – STARTTLS
                props.put("mail.smtp.starttls.enable",   "true");
                props.put("mail.smtp.starttls.required", "true");
                props.put("mail.smtp.ssl.trust",         smtpHost);
                props.put("mail.smtp.ssl.protocols",     "TLSv1.2");
            }

            final String user = smtpUser;
            final String pass = smtpPassword;

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("✅ CORREO ENVIADO correctamente via puerto " + port);
            lastError = "";
            return true;

        } catch (AuthenticationFailedException e) {
            lastError = "ERROR_AUTH (puerto " + port + "): " + e.getMessage();
            System.err.println("❌ " + lastError);
            System.err.println("   → Verifica la contraseña de aplicación de Google en myaccount.google.com/apppasswords");
        } catch (MessagingException e) {
            lastError = "ERROR_SMTP (puerto " + port + "): " + e.getMessage();
            System.err.println("❌ " + lastError);
            // Imprimir causa raíz si existe
            if (e.getCause() != null) {
                System.err.println("   → Causa raíz: " + e.getCause().getClass().getSimpleName() + ": " + e.getCause().getMessage());
            }
        } catch (Exception e) {
            lastError = "ERROR_INESPERADO (puerto " + port + "): " + e.getClass().getSimpleName() + ": " + e.getMessage();
            System.err.println("❌ " + lastError);
        }
        return false;
    }

    public boolean sendContactEmail(String nombre, String email, String telefono,
                                    String asunto, String mensaje, String adminEmail) {
        String htmlBody = buildContactEmailHtml(nombre, email, telefono, asunto, mensaje);
        String subject  = "Nuevo contacto: " + asunto;
        return sendEmail(adminEmail, subject, htmlBody);
    }

    public boolean sendConfirmationEmail(String userEmail, String userName) {
        String htmlBody = buildConfirmationEmailHtml(userName);
        String subject  = "Confirmación de mensaje recibido";
        return sendEmail(userEmail, subject, htmlBody);
    }

    private String buildContactEmailHtml(String nombre, String email, String telefono,
                                         String asunto, String mensaje) {
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style>" +
                "body{font-family:Arial,sans-serif;background:#f5f5f5;padding:20px}" +
                ".box{max-width:600px;margin:auto;background:#fff;padding:25px;border-radius:8px}" +
                "h2{color:#007bff;border-bottom:3px solid #007bff;padding-bottom:10px}" +
                ".lbl{font-weight:bold;color:#555}" +
                ".msg{background:#f9f9f9;border-left:4px solid #007bff;padding:15px;margin-top:15px}" +
                ".ft{color:#999;font-size:12px;text-align:center;margin-top:20px}" +
                "</style></head><body><div class=\"box\">" +
                "<h2>📧 Nuevo Contacto</h2>" +
                "<p><span class=\"lbl\">Nombre:</span> " + nombre + "</p>" +
                "<p><span class=\"lbl\">Email:</span> <a href=\"mailto:" + email + "\">" + email + "</a></p>" +
                "<p><span class=\"lbl\">Teléfono:</span> " + (telefono.isEmpty() ? "No proporcionado" : telefono) + "</p>" +
                "<p><span class=\"lbl\">Asunto:</span> " + asunto + "</p>" +
                "<div class=\"msg\"><strong>Mensaje:</strong><br><br>" + mensaje.replace("\n", "<br>") + "</div>" +
                "<div class=\"ft\">Enviado desde el formulario de contacto</div>" +
                "</div></body></html>";
    }

    private String buildConfirmationEmailHtml(String userName) {
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style>" +
                "body{font-family:Arial,sans-serif;background:#f5f5f5;padding:20px}" +
                ".box{max-width:600px;margin:auto;background:#fff;padding:25px;border-radius:8px}" +
                "h2{color:#28a745;border-bottom:3px solid #28a745;padding-bottom:10px}" +
                "p{color:#333;line-height:1.8}" +
                "</style></head><body><div class=\"box\">" +
                "<h2>✅ ¡Gracias por Contactarnos!</h2>" +
                "<p>Hola " + userName + ",</p>" +
                "<p>Hemos recibido tu mensaje correctamente. Nos pondremos en contacto contigo pronto.</p>" +
                "<p>Saludos,<br><strong>El equipo de Rolfing</strong></p>" +
                "</div></body></html>";
    }

    private String maskEmail(String email) {
        if (email == null || email.isEmpty()) return "No configurado";
        if (email.length() <= 5) return "***";
        return email.substring(0, 3) + "***" + email.substring(email.length() - 4);
    }

    private String formatBoolean(boolean value) {
        return value ? "✅ Habilitado" : "❌ Deshabilitado";
    }
}
