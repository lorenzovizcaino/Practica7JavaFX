package com.example.practica7javafx;

public class Pizza {
    private Usuario usuario;
    private double precio;

    public Pizza(Usuario usuario, double precio) {
        this.usuario = usuario;
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
