package com.rolfing.servlet;

import javax.servlet.ServletException;
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
public class ContactServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    /**
     * Maneja solicitudes GET
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
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
            throws ServletException, IOException {
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

            // Aquí iría la lógica para guardar el mensaje en base de datos
            // o enviar un email (requiere configuración adicional)
            System.out.println("Nuevo contacto recibido:");
            System.out.println("  Nombre: " + nombre);
            System.out.println("  Email: " + email);
            System.out.println("  Teléfono: " + telefono);
            System.out.println("  Asunto: " + asunto);
            System.out.println("  Mensaje: " + mensaje);

            // Responder con éxito
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.println("{\"success\": true, \"message\": \"Mensaje enviado exitosamente.\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.println("{\"success\": false, \"message\": \"Error al procesar la solicitud.\"}");
            }
            e.printStackTrace();
        }
    }

    /**
     * Valida si un email tiene formato válido
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
