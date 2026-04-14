package com.rolfing.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Modelo para representar un contacto/solicitud de información
 */
public class Contact implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String asunto;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean leido;

    // ===================================
    // CONSTRUCTORES
    // ===================================

    public Contact() {
        this.fechaCreacion = LocalDateTime.now();
        this.leido = false;
    }

    public Contact(String nombre, String email, String telefono, String asunto, String mensaje) {
        this();
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    // ===================================
    // GETTERS Y SETTERS
    // ===================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    // ===================================
    // MÉTODOS
    // ===================================

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", asunto='" + asunto + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", leido=" + leido +
                '}';
    }
}
