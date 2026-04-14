package com.rolfing.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Gestor centralizado de configuración
 * Lee desde variables de entorno y propiedades
 */
public class ConfigManager {
    
    private static ConfigManager instance;
    private Properties properties;
    
    private ConfigManager() {
        loadProperties();
    }
    
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
    
    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = this.getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
                System.out.println("✅ config.properties cargado exitosamente");
            } else {
                System.out.println("⚠️  config.properties no encontrado, usando defaults");
            }
        } catch (IOException e) {
            System.err.println("❌ Error cargando config.properties: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene valor de variable de entorno o propiedad
     * Prioriza: variable de entorno > propiedad > valor por defecto
     */
    public String get(String envVar, String propKey, String defaultValue) {
        // 1. Intenta variable de entorno
        String envValue = System.getenv(envVar);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        
        // 2. Intenta propiedad
        String propValue = properties.getProperty(propKey);
        if (propValue != null && !propValue.isEmpty()) {
            return propValue;
        }
        
        // 3. Retorna valor por defecto
        return defaultValue;
    }
    

    
    public boolean getBoolean(String propKey, boolean defaultValue) {
        String value = properties.getProperty(propKey);
        if (value != null && !value.isEmpty()) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
}
