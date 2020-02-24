package com.example.appconductor.Solicitudes;

public class eSolicitud {
    private String id;
    private String nombre_cliente;
    private Double latitud;
    private Double longitud;
    private int estado;



    public eSolicitud(String id, String nombre_cliente, Double latitud, Double longitud, int estado) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }



    public eSolicitud() {
    }

    public eSolicitud(String id, String nombre_cliente, Double latitud, Double longitud) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public eSolicitud(String nombre_cliente, Double latitud, Double longitud) {
        this.nombre_cliente = nombre_cliente;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
