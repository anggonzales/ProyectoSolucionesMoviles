package com.example.appcliente;

public class Usuarios {
    private String id;
    private String usuario;
    private String telefono;
    private String pass;
    private String correo;

    public Usuarios() {
    }
    public Usuarios(String usuario, String telefono, String pass, String correo) {
        this.usuario = usuario;
        this.telefono = telefono;
        this.pass = pass;
        this.correo = correo;
    }
    public Usuarios(String id, String usuario, String telefono, String pass, String correo) {
        this.id = id;
        this.usuario = usuario;
        this.telefono = telefono;
        this.pass = pass;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
