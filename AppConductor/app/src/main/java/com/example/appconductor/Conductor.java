package com.example.appconductor;

public class Conductor {

    private String id;
    private String usuario;
    private String telefono;
    private String clave;
    private String correo;


    public Conductor() {

    }

    public Conductor(String id, String usuario, String telefono, String clave, String correo) {
        this.id = id;
        this.usuario = usuario;
        this.telefono = telefono;
        this.clave = clave;
        this.correo = correo;
    }


    public Conductor(String usuario, String clave, String correo) {
        this.usuario = usuario;
        this.clave = clave;
        this.correo = correo;
    }

    public Conductor(String usuario, String telefono, String clave, String correo) {
        this.usuario = usuario;
        this.telefono = telefono;
        this.clave = clave;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
