package com.example.trabajo01_multimedia.clases;

import java.io.Serializable;

public class Chinpokomon implements Serializable{
    private int codigo;
    private String nombre;
    private int nivel;
    private String tipo;
    private String movimiento;


    public Chinpokomon() {

    }
    public Chinpokomon(int codigo, String nombre, int nivel, String tipo, String movimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.movimiento = movimiento;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }
}
