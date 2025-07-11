/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;

/**
 *
 * @author Thoka
 */
public abstract class Carta {
    private int id;
    private boolean bocaArriba;
    private boolean colocada;

    // NUEVO: Posición en el tablero
    private int fila;
    private int columna;

    public Carta(int id) {
        this.id = id;
        this.bocaArriba = false;
        this.colocada = false;
    }

    public void voltear() {
        bocaArriba = !bocaArriba;
    }

    public void colocar() {
        colocada = true;
    }

    // 👇 NUEVO: Métodos para la posición
    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    // Getters y setters normales
    public int getId() {
        return id;
    }

    public boolean isBocaArriba() {
        return bocaArriba;
    }

    public boolean isColocada() {
        return colocada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBocaArriba(boolean bocaArriba) {
        this.bocaArriba = bocaArriba;
    }

    public void setColocada(boolean colocada) {
        this.colocada = colocada;
    }
}
