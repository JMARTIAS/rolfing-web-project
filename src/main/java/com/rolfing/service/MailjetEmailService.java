package com.rolfing.service;

import com.rolfing.config.ConfigManager;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servicio de email usando Mailjet API (via HTTP REST)
 * Completamente gratis: 200 emails/día, sin expiración, sin tarjeta de crédito necesaria
 */
public class MailjetEmailService {

    private MailjetClient mailjetClient;
    private String fromEmail;
    private String fromName;
    private boolean enabled;
    private String lastError = "";

    public MailjetEmailService() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        ConfigManager config = ConfigManager.getInstance();
        String apiKey = config.get("MAILJET_API_KEY", "mailjet.api.key", "");
        String apiSecret = config.get("MAILJET_API_SECRET", "mailjet.api.secret", "");

        this.fromEmail = config.get("MAILJET_FROM_EMAIL", "mailjet.from.email",
                                    config.get("MAIL_SMTP_USER", "email.smtp.user", "noreply@rolfing.com"));
        this.fromName = "Rolfing Terapia";

        if (apiKey != null && !apiKey.isEmpty() && apiSecret != null && !apiSecret.isEmpty()) {
            ClientOptions options = ClientOptions.builder()
                    .apiKey(apiKey)
                    .apiSecretKey(apiSecret)
                    .build();
            this.mailjetClient = new MailjetClient(options);
            this.enabled = true;
        } else {
            this.enabled = false;
        }

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   MAILJET CONFIGURACIÓN CARGADA        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Habilitado: " + (enabled ? "✅ SÍ" : "❌ NO"));
        System.out.println("║ API Key:    " + (enabled ? maskKey(apiKey) : "no configurada"));
        System.out.println("║ From Email: " + fromEmail);
        System.out.println("║ From Name:  " + fromName);
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    public String getLastError() {
        return lastError;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("\n--- MAILJET: INTENTO DE ENVÍO ---");
        System.out.println("Para:   " + to);
        System.out.println("Asunto: " + subject);

        if (!enabled) {
            lastError = "Mailjet no está configurado (falta MAILJET_API_KEY o MAILJET_API_SECRET)";
            System.err.println("❌ " + lastError);
            return false;
        }

        try {
            System.out.println("🚀 Enviando via Mailjet API...");

            MailjetRequest request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", fromEmail)
                                            .put("Name", fromName))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", to)))
                                    .put(Emailv31.Message.SUBJECT, subject)
                                    .put(Emailv31.Message.HTMLPART, body)
                            ));

            MailjetResponse response = mailjetClient.post(request);

            System.out.println("📨 Status Code: " + response.getStatus());

            if (response.getStatus() == 200) {
                System.out.println("✅ CORREO ENVIADO exitosamente via Mailjet");
                lastError = "";
                return true;
            } else {
                lastError = "Mailjet respondió con status " + response.getStatus() + ": " + response.getData().toString();
                System.err.println("❌ " + lastError);
                return false;
            }

        } catch (Exception e) {
            lastError = "Error Mailjet: " + e.getClass().getSimpleName() + " - " + e.getMessage();
            System.err.println("❌ " + lastError);
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean sendContactEmail(String nombre, String email, String telefono,
                                    String asunto, String mensaje, String adminEmail) {
        String htmlBody = buildContactHtml(nombre, email, telefono, asunto, mensaje);
        return sendEmail(adminEmail, "Nuevo contacto: " + asunto, htmlBody);
    }

    public boolean sendConfirmationEmail(String userEmail, String userName) {
        String htmlBody = buildConfirmationHtml(userName);
        return sendEmail(userEmail, "Confirmación de mensaje recibido", htmlBody);
    }

    private String buildContactHtml(String nombre, String email, String telefono,
                                   String asunto, String mensaje) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <style>\n" +
                "    body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 20px; }\n" +
                "    .container { max-width: 600px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }\n" +
                "    .header { color: #333; border-bottom: 3px solid #007bff; padding-bottom: 15px; margin-bottom: 20px; }\n" +
                "    .header h2 { margin: 0; color: #007bff; }\n" +
                "    .field { margin: 12px 0; }\n" +
                "    .label { font-weight: bold; color: #555; display: inline-block; width: 100px; }\n" +
                "    .value { color: #333; }\n" +
                "    .message-box { background-color: #f9f9f9; border-left: 4px solid #007bff; padding: 15px; margin-top: 20px; }\n" +
                "    .footer { color: #999; font-size: 12px; margin-top: 20px; text-align: center; border-top: 1px solid #eee; padding-top: 15px; }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <div class=\"header\">\n" +
                "      <h2>📧 Nuevo Contacto Recibido</h2>\n" +
                "    </div>\n" +
                "    <div class=\"field\"><span class=\"label\">Nombre:</span> <span class=\"value\">" + nombre + "</span></div>\n" +
                "    <div class=\"field\"><span class=\"label\">Email:</span> <span class=\"value\"><a href=\"mailto:" + email + "\">" + email + "</a></span></div>\n" +
                "    <div class=\"field\"><span class=\"label\">Teléfono:</span> <span class=\"value\">" + (telefono.isEmpty() ? "No proporcionado" : telefono) + "</span></div>\n" +
                "    <div class=\"field\"><span class=\"label\">Asunto:</span> <span class=\"value\">" + asunto + "</span></div>\n" +
                "    <div class=\"message-box\">\n" +
                "      <strong>Mensaje:</strong><br><br>\n" +
                "      " + mensaje.replace("\n", "<br>") + "\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "      <p>Este mensaje fue enviado desde el formulario de contacto de tu sitio web.</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }

    private String buildConfirmationHtml(String userName) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <style>\n" +
                "    body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 20px; }\n" +
                "    .container { max-width: 600px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }\n" +
                "    .header { color: #28a745; border-bottom: 3px solid #28a745; padding-bottom: 15px; margin-bottom: 20px; }\n" +
                "    .header h2 { margin: 0; color: #28a745; }\n" +
                "    .content { color: #333; line-height: 1.8; }\n" +
                "    .footer { color: #999; font-size: 12px; margin-top: 20px; text-align: center; border-top: 1px solid #eee; padding-top: 15px; }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <div class=\"header\">\n" +
                "      <h2>✅ ¡Gracias por Contactarnos!</h2>\n" +
                "    </div>\n" +
                "    <div class=\"content\">\n" +
                "      <p>Hola " + userName + ",</p>\n" +
                "      <p>Hemos recibido tu mensaje correctamente. Uno de nuestros profesionales se pondrá en contacto contigo pronto.</p>\n" +
                "      <p>Esperamos poder ayudarte.</p>\n" +
                "      <br>\n" +
                "      <p>Saludos,<br><strong>El equipo de Rolfing</strong></p>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "      <p>Este es un correo automático, por favor no respondas a esta dirección.</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }

    private String maskKey(String key) {
        if (key == null || key.length() < 10) return "***";
        return key.substring(0, 5) + "..." + key.substring(key.length() - 5);
    }
}
