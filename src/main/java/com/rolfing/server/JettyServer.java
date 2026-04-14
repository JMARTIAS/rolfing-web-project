package com.rolfing.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Servidor Jetty embebido para ejecutar la aplicación web de Rolfing
 */
public class JettyServer {
    
    private static final int PORT = 8080;
    private static final String CONTEXT_PATH = "/rolfing";
    private static final String HOST = "0.0.0.0";
    
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        
        // Configurar conector HTTP explícitamente
        ServerConnector connector = new ServerConnector(server);
        connector.setHost(HOST);
        connector.setPort(PORT);
        server.addConnector(connector);
        
        // Crear contexto web
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath(CONTEXT_PATH);
        
        // Buscar el directorio webapp en el proyecto
        String webappPath = "src/main/webapp";
        webapp.setResourceBase(webappPath);
        
        // Configurar descriptores
        webapp.setDescriptor(webappPath + "/WEB-INF/web.xml");
        
        server.setHandler(webapp);
        
        try {
            server.start();
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✅ SERVIDOR INICIADO CORRECTAMENTE");
            System.out.println("=".repeat(60));
            System.out.println("🌐 Accede a: http://localhost:" + PORT + CONTEXT_PATH);
            System.out.println("📍 Host: " + HOST + ":" + PORT);
            System.out.println("📁 Contexto: " + CONTEXT_PATH);
            System.out.println("⏹️  Presiona Ctrl+C para detener el servidor");
            System.out.println("=".repeat(60) + "\n");
            server.join();
        } catch (Exception e) {
            System.err.println("❌ Error al iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
