/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

/**
 *
 * @author Thoka
 */
public class Movimiento {
     private int turno;
    private int fila1, col1;
    private int fila2, col2;
    private boolean fueEmparejada;
    
    public Movimiento(int turno, int fila1, int col1, int fila2, int col2, boolean fueEmparejada) {
    this.turno = turno;
    this.fila1 = fila1;
    this.col1 = col1;
    this.fila2 = fila2;
    this.col2 = col2;
    this.fueEmparejada = fueEmparejada;
}

    public int getTurno() {
        return turno;
    }

    public int getFila1() {
        return fila1;
    }

    public int getCol1() {
        return col1;
    }

    public int getFila2() {
        return fila2;
    }

    public int getCol2() {
        return col2;
    }

    public boolean isFueEmparejada() {
        return fueEmparejada;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setFila1(int fila1) {
        this.fila1 = fila1;
    }

    public void setCol1(int col1) {
        this.col1 = col1;
    }

    public void setFila2(int fila2) {
        this.fila2 = fila2;
    }

    public void setCol2(int col2) {
        this.col2 = col2;
    }

    public void setFueEmparejada(boolean fueEmparejada) {
        this.fueEmparejada = fueEmparejada;
    }
    
    public boolean fueEmparejado() {
    return fueEmparejada;
}
    
}
