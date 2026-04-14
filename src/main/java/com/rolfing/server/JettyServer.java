package com.rolfing.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Servidor Jetty embebido para ejecutar la aplicación web de Rolfing
 */
public class JettyServer {
    
    private static final String CONTEXT_PATH = "/";
    private static final String HOST = "0.0.0.0";
    
    public static void main(String[] args) throws Exception {
        // Obtener puerto de variable de entorno (Render lo proporciona)
        // Si no existe, usar 8080 como fallback para desarrollo local
        String portStr = System.getenv("PORT");
        int port = portStr != null ? Integer.parseInt(portStr) : 8080;
        
        System.out.println("\n📋 Configuración del servidor:");
        System.out.println("   Puerto: " + port);
        System.out.println("   Host: " + HOST);
        System.out.println("   Contexto: " + CONTEXT_PATH);
        
        Server server = new Server();
        
        // Configurar conector HTTP explícitamente
        ServerConnector connector = new ServerConnector(server);
        connector.setHost(HOST);
        connector.setPort(port);
        server.addConnector(connector);
        
        // Crear contexto web
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath(CONTEXT_PATH);
        
        // En Render, el archivo se encuentra en el directorio actual
        String webappPath = "src/main/webapp";
        
        // Intentar encontrar la ruta correcta
        java.nio.file.Path path1 = java.nio.file.Paths.get(webappPath);
        java.nio.file.Path path2 = java.nio.file.Paths.get("build/resources/main");
        
        if (java.nio.file.Files.exists(path1)) {
            webapp.setResourceBase(webappPath);
            webapp.setDescriptor(webappPath + "/WEB-INF/web.xml");
            System.out.println("   Usando recurso local: " + webappPath);
        } else if (java.nio.file.Files.exists(path2)) {
            webapp.setResourceBase(path2.toAbsolutePath().toString());
            System.out.println("   Usando recurso build: " + path2);
        } else {
            System.out.println("   ⚠️  Usando recurso por defecto (posiblemente en classpath)");
        }
        
        server.setHandler(webapp);
        
        try {
            server.start();
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✅ SERVIDOR INICIADO CORRECTAMENTE");
            System.out.println("=".repeat(60));
            System.out.println("🌐 Accede a: http://localhost:" + port);
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
