package com.rolfing.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import java.nio.file.Paths;

/**
 * Servidor Jetty embebido (Jetty 10 - compatible con javax.servlet)
 */
public class JettyServer {

    public static void main(String[] args) throws Exception {
        String portStr = System.getenv("PORT");
        int port = portStr != null ? Integer.parseInt(portStr) : 8080;

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  INICIANDO SERVIDOR ROLFING");
        System.out.println("=".repeat(60));

        String webappPath = Paths.get("src/main/webapp").toAbsolutePath().toString();
        String webXmlPath = webappPath + "/WEB-INF/web.xml";

        System.out.println("  Puerto:  " + port);
        System.out.println("  Webapp:  " + webappPath);
        System.out.flush();

        Server server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost("0.0.0.0");
        server.addConnector(connector);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase(webappPath);
        webapp.setDescriptor(webXmlPath);
        webapp.setParentLoaderPriority(true);

        server.setHandler(webapp);

        server.start();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  SERVIDOR LISTO");
        System.out.println("  Accede a: http://localhost:" + port + "/");
        System.out.println("  Ctrl+C para detener");
        System.out.println("=".repeat(60) + "\n");
        System.out.flush();

        server.join();
    }
}
