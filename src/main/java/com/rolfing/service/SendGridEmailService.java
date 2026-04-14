package com.rolfing.service;

import com.rolfing.config.ConfigManager;

/**
 * Servicio de email usando SendGrid (alternativa cuando SMTP está bloqueado)
 * SendGrid no requiere conexiones SMTP salientes, solo HTTP
 */
public class SendGridEmailService {

    private String sendGridApiKey;
    private String fromEmail;
    private boolean enabled;
    private String lastError = "";

    public SendGridEmailService() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        ConfigManager config = ConfigManager.getInstance();
        this.sendGridApiKey = config.get("SENDGRID_API_KEY", "sendgrid.api.key", "");
        this.fromEmail = config.get("SENDGRID_FROM_EMAIL", "sendgrid.from.email", 
                                    config.get("MAIL_SMTP_USER", "email.smtp.user", "noreply@rolfing.com"));
        this.enabled = !sendGridApiKey.isEmpty();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   SENDGRID CONFIGURACIÓN CARGADA       ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Habilitado: " + (enabled ? "✅ SÍ" : "❌ NO"));
        System.out.println("║ API Key:    " + (enabled ? maskApiKey(sendGridApiKey) : "no configurada"));
        System.out.println("║ From Email: " + fromEmail);
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    public String getLastError() {
        return lastError;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("\n--- SENDGRID: INTENTO DE ENVÍO ---");
        System.out.println("Para:   " + to);
        System.out.println("Asunto: " + subject);

        if (!enabled) {
            lastError = "SendGrid no está configurado";
            System.err.println("❌ " + lastError);
            return false;
        }

        try {
            // Para una implementación completa, necesitarías la librería sendgrid-java:
            // implementation("com.sendgrid:sendgrid-java:4.10.1")
            // 
            // Por ahora, retornamos un mensaje indicativo
            System.out.println("⚠️  SendGrid requiere librería adicional (sendgrid-java)");
            System.out.println("📌 Para usar SendGrid, ejecuta:");
            System.out.println("   1. Obtén API Key de SendGrid (https://app.sendgrid.com/settings/api_keys)");
            System.out.println("   2. Agrega variable en Render: SENDGRID_API_KEY=tu_api_key");
            System.out.println("   3. El email se enviará via HTTP en lugar de SMTP");
            
            lastError = "SendGrid preparado pero requiere configuración adicional";
            return false;

        } catch (Exception e) {
            lastError = "Error con SendGrid: " + e.getMessage();
            System.err.println("❌ " + lastError);
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean sendContactEmail(String nombre, String email, String telefono,
                                    String asunto, String mensaje, String adminEmail) {
        String htmlBody = buildHtml(nombre, email, telefono, asunto, mensaje);
        return sendEmail(adminEmail, "Nuevo contacto: " + asunto, htmlBody);
    }

    public boolean sendConfirmationEmail(String userEmail, String userName) {
        String htmlBody = buildConfirmationHtml(userName);
        return sendEmail(userEmail, "Confirmación de mensaje recibido", htmlBody);
    }

    private String buildHtml(String nombre, String email, String telefono,
                            String asunto, String mensaje) {
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style>" +
                "body{font-family:Arial,sans-serif;background:#f5f5f5;padding:20px}" +
                ".box{max-width:600px;margin:auto;background:#fff;padding:25px;border-radius:8px}" +
                "h2{color:#007bff;border-bottom:3px solid #007bff;padding-bottom:10px}" +
                ".lbl{font-weight:bold;color:#555}" +
                ".msg{background:#f9f9f9;border-left:4px solid #007bff;padding:15px;margin-top:15px}" +
                "</style></head><body><div class=\"box\">" +
                "<h2>📧 Nuevo Contacto</h2>" +
                "<p><span class=\"lbl\">Nombre:</span> " + nombre + "</p>" +
                "<p><span class=\"lbl\">Email:</span> " + email + "</p>" +
                "<p><span class=\"lbl\">Teléfono:</span> " + (telefono.isEmpty() ? "No proporcionado" : telefono) + "</p>" +
                "<p><span class=\"lbl\">Asunto:</span> " + asunto + "</p>" +
                "<div class=\"msg\"><strong>Mensaje:</strong><br><br>" + mensaje.replace("\n", "<br>") + "</div>" +
                "</div></body></html>";
    }

    private String buildConfirmationHtml(String userName) {
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style>" +
                "body{font-family:Arial,sans-serif;background:#f5f5f5;padding:20px}" +
                ".box{max-width:600px;margin:auto;background:#fff;padding:25px;border-radius:8px}" +
                "h2{color:#28a745;border-bottom:3px solid #28a745;padding-bottom:10px}" +
                "p{color:#333;line-height:1.8}" +
                "</style></head><body><div class=\"box\">" +
                "<h2>✅ ¡Gracias por Contactarnos!</h2>" +
                "<p>Hola " + userName + ",</p>" +
                "<p>Hemos recibido tu mensaje. Nos pondremos en contacto pronto.</p>" +
                "<p>Saludos,<br><strong>El equipo de Rolfing</strong></p>" +
                "</div></body></html>";
    }

    private String maskApiKey(String apiKey) {
        if (apiKey == null || apiKey.length() < 10) return "***";
        return apiKey.substring(0, 5) + "..." + apiKey.substring(apiKey.length() - 5);
    }
}
