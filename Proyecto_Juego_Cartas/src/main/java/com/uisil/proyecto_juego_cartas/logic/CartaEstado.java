/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

public class CartaEstado {
    private int id;
    private boolean colocada;
    private boolean bocaArriba;
    private boolean esBonus;
    private boolean esPenalizacion;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isColocada() { return colocada; }
    public void setColocada(boolean colocada) { this.colocada = colocada; }

    public boolean isBocaArriba() { return bocaArriba; }
    public void setBocaArriba(boolean bocaArriba) { this.bocaArriba = bocaArriba; }

    public boolean isEsBonus() { return esBonus; }
    public void setEsBonus(boolean esBonus) { this.esBonus = esBonus; }

    public boolean isEsPenalizacion() { return esPenalizacion; }
    public void setEsPenalizacion(boolean esPenalizacion) { this.esPenalizacion = esPenalizacion; }
}