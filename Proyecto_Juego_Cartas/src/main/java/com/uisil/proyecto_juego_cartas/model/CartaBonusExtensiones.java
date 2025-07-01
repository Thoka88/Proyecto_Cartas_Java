/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;

/**
 *
 * @author Yordi
 */
public class CartaBonusExtensiones extends Carta {

    private boolean doblePuntos;
    private boolean esComodin;

    public CartaBonusExtensiones(int id) {
        super(id);
    }

    public boolean isDoblePuntos() {
        return doblePuntos;
    }

    public void setDoblePuntos(boolean doblePuntos) {
        this.doblePuntos = doblePuntos;
    }

    public boolean isEsComodin() {
        return esComodin;
    }

    public void setEsComodin(boolean esComodin) {
        this.esComodin = esComodin;
    }
}