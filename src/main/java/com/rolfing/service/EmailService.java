package com.rolfing.service;

import com.rolfing.config.ConfigManager;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Servicio para enviar correos electrónicos vía SMTP
 */
public class EmailService {
    
    private String smtpHost;
    private String smtpPort;
    private String smtpUser;
    private String smtpPassword;
    private boolean emailEnabled;
    private final ConfigManager config;

    public EmailService() {
        this.config = ConfigManager.getInstance();
        loadConfiguration();
    }

    private void loadConfiguration() {
        // Obtiene configuración desde variables de entorno o config.properties
        this.smtpHost = config.get("MAIL_SMTP_HOST", "email.smtp.host", "smtp.gmail.com");
        this.smtpPort = config.get("MAIL_SMTP_PORT", "email.smtp.port", "587");
        this.smtpUser = config.get("MAIL_SMTP_USER", "email.smtp.user", "");
        this.smtpPassword = config.get("MAIL_SMTP_PASSWORD", "email.smtp.password", "");
        this.emailEnabled = config.getBoolean("email.enabled", true);
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          CONFIGURACIÓN DE EMAIL CARGADA                  ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║ Habilitado:          " + formatBoolean(emailEnabled));
        System.out.println("║ Host SMTP:           " + smtpHost);
        System.out.println("║ Puerto SMTP:         " + smtpPort);
        System.out.println("║ Usuario SMTP:        " + maskEmail(smtpUser));
        System.out.println("║ Contraseña config.:  " + (smtpPassword != null && !smtpPassword.isEmpty() ? "✅ SÍ" : "❌ NO"));
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        // Validación
        if (emailEnabled) {
            if (smtpUser == null || smtpUser.isEmpty() || smtpUser.contains("tu-email")) {
                System.err.println("⚠️  ADVERTENCIA: Usuario SMTP no está configurado correctamente");
                this.emailEnabled = false;
            }
            if (smtpPassword == null || smtpPassword.isEmpty() || smtpPassword.contains("contraseña")) {
                System.err.println("⚠️  ADVERTENCIA: Contraseña SMTP no está configurada correctamente");
                this.emailEnabled = false;
            }
        }
    }

    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("\n--- INTENTO DE ENVÍO DE CORREO ---");
        System.out.println("Para: " + to);
        System.out.println("Asunto: " + subject);
        
        if (!emailEnabled) {
            System.err.println("❌ EMAIL DESHABILITADO o sin credenciales configuradas");
            return false;
        }
        
        if (smtpUser == null || smtpUser.isEmpty()) {
            System.err.println("❌ Usuario SMTP vacío");
            return false;
        }
        
        if (smtpPassword == null || smtpPassword.isEmpty()) {
            System.err.println("❌ Contraseña SMTP vacía");
            return false;
        }

        try {
            System.out.println("🔧 Configurando propiedades SMTP...");
            Properties mailProps = new Properties();
            mailProps.put("mail.smtp.host", smtpHost);
            mailProps.put("mail.smtp.port", smtpPort);
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.put("mail.smtp.starttls.required", "true");
            mailProps.put("mail.smtp.connectiontimeout", "5000");
            mailProps.put("mail.smtp.timeout", "5000");
            
            System.out.println("🔗 Creando sesión SMTP...");
            Session session = Session.getInstance(mailProps, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpUser, smtpPassword);
                }
            });

            System.out.println("📝 Construyendo mensaje...");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(smtpUser));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");

            System.out.println("📤 Enviando correo...");
            Transport.send(message);
            
            System.out.println("✅ ÉXITO: Correo enviado a " + to);
            return true;

        } catch (AuthenticationFailedException e) {
            System.err.println("❌ ERROR DE AUTENTICACIÓN");
            System.err.println("   Usuario: " + maskEmail(smtpUser));
            System.err.println("   Posible causa: Usuario/Contraseña incorrectos");
            return false;
        } catch (MessagingException e) {
            System.err.println("❌ ERROR AL ENVIAR: " + e.getMessage());
            if (e.getMessage().contains("Connection refused")) {
                System.err.println("   Causa: No se puede conectar a " + smtpHost + ":" + smtpPort);
            } else if (e.getMessage().contains("Invalid")) {
                System.err.println("   Causa: Email inválido o datos incorrectos");
            }
            e.printStackTrace(System.err);
            return false;
        } catch (Exception e) {
            System.err.println("❌ ERROR INESPERADO: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean sendContactEmail(String nombre, String email, String telefono, 
                                    String asunto, String mensaje, String adminEmail) {
        String htmlBody = buildContactEmailHtml(nombre, email, telefono, asunto, mensaje);
        String subject = "Nuevo contacto: " + asunto;
        return sendEmail(adminEmail, subject, htmlBody);
    }

    public boolean sendConfirmationEmail(String userEmail, String userName) {
        String htmlBody = buildConfirmationEmailHtml(userName);
        String subject = "Confirmación de mensaje recibido";
        return sendEmail(userEmail, subject, htmlBody);
    }

    private String buildContactEmailHtml(String nombre, String email, String telefono, 
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

    private String buildConfirmationEmailHtml(String userName) {
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

    private String maskEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "No configurado";
        }
        if (email.length() <= 5) {
            return "***";
        }
        return email.substring(0, 3) + "***" + email.substring(email.length() - 4);
    }

    private String formatBoolean(boolean value) {
        return value ? "✅ Habilitado  " : "❌ Deshabilitado";
    }
}
