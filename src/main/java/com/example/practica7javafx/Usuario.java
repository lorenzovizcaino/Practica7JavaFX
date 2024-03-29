package com.example.practica7javafx;

import javafx.scene.image.Image;

public class Usuario {
    private int id;
    private String nombre;
    private String urlImagen;
    private double credito;

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public Usuario(int id, String nombre, String urlImagen, double credito) {
        this.id = id;
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        this.credito=credito;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }


}
