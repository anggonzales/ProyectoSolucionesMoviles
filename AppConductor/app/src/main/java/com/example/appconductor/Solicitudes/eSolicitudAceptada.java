package com.example.appconductor.Solicitudes;

public class eSolicitudAceptada {
    private String id;
    private String nombre_cliente;
    private String nombre_conductor;
    private Double latitud;
    private Double longitud;
    private Double costo;

    public eSolicitudAceptada() {
    }

    public eSolicitudAceptada(String id, String nombre_cliente, String nombre_conductor, Double latitud, Double longitud, Double costo) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.nombre_conductor = nombre_conductor;
        this.latitud = latitud;
        this.longitud = longitud;
        this.costo = costo;
    }

    public eSolicitudAceptada(String nombre_cliente, String nombre_conductor, Double latitud, Double longitud, Double costo) {
        this.nombre_cliente = nombre_cliente;
        this.nombre_conductor = nombre_conductor;
        this.latitud = latitud;
        this.longitud = longitud;
        this.costo = costo;
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

    public String getNombre_conductor() {
        return nombre_conductor;
    }

    public void setNombre_conductor(String nombre_conductor) {
        this.nombre_conductor = nombre_conductor;
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
