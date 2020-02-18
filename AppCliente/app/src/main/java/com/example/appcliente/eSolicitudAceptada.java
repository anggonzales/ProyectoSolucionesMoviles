package com.example.appcliente;

public class eSolicitudAceptada {
    private String id;
    private String nombre_cliente_1;
    private String nombre_cliente_2;
    private String nombre_cliente_3;
    private String nombre_cliente_4;
    private String nombre_conductor;
    private Double latitud;
    private Double longitud;
    private Double costo;
    private int estado;

    public eSolicitudAceptada() {
    }


    public eSolicitudAceptada(String nombre_cliente_1, String nombre_cliente_2, String nombre_conductor, Double latitud, Double longitud, Double costo, int estado) {
        this.id = id;
        this.nombre_cliente_1 = nombre_cliente_1;
        this.nombre_cliente_2 = nombre_cliente_2;
        this.nombre_conductor = nombre_conductor;
        this.latitud = latitud;
        this.longitud = longitud;
        this.costo = costo;
        this.estado = estado;
    }

    public eSolicitudAceptada(String nombre_cliente_1, String nombre_cliente_2, String nombre_cliente_3, String nombre_conductor, Double latitud, Double longitud, Double costo, int estado) {
        this.nombre_cliente_1 = nombre_cliente_1;
        this.nombre_cliente_2 = nombre_cliente_2;
        this.nombre_cliente_3 = nombre_cliente_3;
        this.nombre_conductor = nombre_conductor;
        this.latitud = latitud;
        this.longitud = longitud;
        this.costo = costo;
        this.estado = estado;
    }

    public eSolicitudAceptada(String nombre_cliente_1, String nombre_cliente_2, String nombre_cliente_3, String nombre_cliente_4, String nombre_conductor, Double latitud, Double longitud, Double costo, int estado) {
        this.nombre_cliente_1 = nombre_cliente_1;
        this.nombre_cliente_2 = nombre_cliente_2;
        this.nombre_cliente_3 = nombre_cliente_3;
        this.nombre_cliente_4 = nombre_cliente_4;
        this.nombre_conductor = nombre_conductor;
        this.latitud = latitud;
        this.longitud = longitud;
        this.costo = costo;
        this.estado = estado;
    }

    public eSolicitudAceptada(String id, String nombre_cliente_1, String nombre_cliente_2, String nombre_cliente_3, String nombre_cliente_4, String nombre_conductor, Double latitud, Double longitud, Double costo, int estado) {
        this.id = id;
        this.nombre_cliente_1 = nombre_cliente_1;
        this.nombre_cliente_2 = nombre_cliente_2;
        this.nombre_cliente_3 = nombre_cliente_3;
        this.nombre_cliente_4 = nombre_cliente_4;
        this.nombre_conductor = nombre_conductor;
        this.latitud = latitud;
        this.longitud = longitud;
        this.costo = costo;
        this.estado = estado;
    }

    public String getNombre_cliente_1() {
        return nombre_cliente_1;
    }

    public void setNombre_cliente_1(String nombre_cliente_1) {
        this.nombre_cliente_1 = nombre_cliente_1;
    }

    public String getNombre_cliente_2() {
        return nombre_cliente_2;
    }

    public void setNombre_cliente_2(String nombre_cliente_2) {
        this.nombre_cliente_2 = nombre_cliente_2;
    }

    public String getNombre_cliente_3() {
        return nombre_cliente_3;
    }

    public void setNombre_cliente_3(String nombre_cliente_3) {
        this.nombre_cliente_3 = nombre_cliente_3;
    }

    public String getNombre_cliente_4() {
        return nombre_cliente_4;
    }

    public void setNombre_cliente_4(String nombre_cliente_4) {
        this.nombre_cliente_4 = nombre_cliente_4;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
