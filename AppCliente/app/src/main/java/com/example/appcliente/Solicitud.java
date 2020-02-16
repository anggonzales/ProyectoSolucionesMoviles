package com.example.appcliente;

public class Solicitud {
    private String id;
    private String nombre_cliente;
    private Double latitud;
    private Double longitud;

    public Solicitud() {
    }

    public Solicitud(String id, String nombre_cliente, Double latitud, Double longitud) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Solicitud(String nombre_cliente, Double latitud, Double longitud) {
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
}
