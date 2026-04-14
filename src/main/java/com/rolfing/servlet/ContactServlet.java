package com.rolfing.servlet;

import com.rolfing.service.EmailService;
import com.rolfing.config.ConfigManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet para manejar solicitudes de contacto desde el formulario
 */
@WebServlet("/api/contact")
@MultipartConfig
public class ContactServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private EmailService emailService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.emailService = new EmailService();
    }

    /**
     * Maneja solicitudes GET
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("{\"error\": \"Método GET no permitido. Use POST.\"}");
        }
    }

    /**
     * Maneja solicitudes POST
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        try {
            // Obtener parámetros del formulario
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String asunto = request.getParameter("asunto");
            String mensaje = request.getParameter("mensaje");

            // Validar campos requeridos
            if (nombre == null || nombre.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                mensaje == null || mensaje.trim().isEmpty()) {
                
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                try (PrintWriter out = response.getWriter()) {
                    out.println("{\"success\": false, \"message\": \"Campos requeridos no completados.\"}");
                }
                return;
            }

            // Validar email
            if (!isValidEmail(email)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                try (PrintWriter out = response.getWriter()) {
                    out.println("{\"success\": false, \"message\": \"Email inválido.\"}");
                }
                return;
            }
            
            // Sanitizar entrada
            nombre = sanitizeInput(nombre);
            asunto = sanitizeInput(asunto != null ? asunto : "Sin asunto");
            telefono = sanitizeInput(telefono != null ? telefono : "");
            mensaje = sanitizeInput(mensaje);

            // Obtener email del administrador desde configuración
            String adminEmail = getAdminEmail();
            
            // Log de recepción
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║            NUEVO CONTACTO RECIBIDO                      ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║ Nombre:      " + String.format("%-40s", nombre) + " ║");
            System.out.println("║ Email:       " + String.format("%-40s", email) + " ║");
            System.out.println("║ Teléfono:    " + String.format("%-40s", telefono) + " ║");
            System.out.println("║ Asunto:      " + String.format("%-40s", asunto) + " ║");
            System.out.println("║ Admin Email: " + String.format("%-40s", adminEmail) + " ║");
            System.out.println("╚════════════════════════════════════════════════════════╝\n");
            
            // Enviar emails
            System.out.println("📤 Intentando enviar emails...");
            boolean emailSentToAdmin = emailService.sendContactEmail(nombre, email, telefono, asunto, mensaje, adminEmail);
            System.out.println("   → Correo al admin: " + (emailSentToAdmin ? "✅ ENVIADO" : "❌ FALLÓ"));
            
            boolean emailSentToUser = emailService.sendConfirmationEmail(email, nombre);
            System.out.println("   → Confirmación al usuario: " + (emailSentToUser ? "✅ ENVIADA" : "❌ FALLÓ"));

            // Responder con éxito
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                StringBuilder response_msg = new StringBuilder("{\"success\": true, \"message\": \"Mensaje recibido exitosamente.");
                
                if (!emailSentToAdmin && !emailSentToUser) {
                    response_msg.append(" (Nota: Los emails no pudieron ser enviados, pero tu mensaje fue registrado.)\"}");
                } else if (!emailSentToAdmin || !emailSentToUser) {
                    response_msg.append(" (Parcial: Algunos emails fallaron, revisa los logs.)\"}");
                } else {
                    response_msg.append("\"}");
                }
                
                out.println(response_msg);
            }

        } catch (Exception e) {
            System.err.println("Error al procesar contacto: " + e.getMessage());
            e.printStackTrace(System.err);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.println("{\"success\": false, \"message\": \"Error al procesar la solicitud.\"}");
            }
        }
    }

    /**
     * Valida si un email tiene formato válido
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    /**
     * Sanitiza la entrada para prevenir inyecciones HTML
     */
    private String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        return input
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .trim();
    }
    
    /**
     * Obtiene el email del administrador desde la configuración
     */
    private String getAdminEmail() {
        ConfigManager config = ConfigManager.getInstance();
        return config.get("BUSINESS_EMAIL", "contact.email", "info@rolfing.com");
    }
}
