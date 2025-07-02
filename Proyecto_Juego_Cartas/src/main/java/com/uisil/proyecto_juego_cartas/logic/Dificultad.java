/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

/**
 *
 * @author Thoka
 */
public enum Dificultad {
    FACIL(4,4,50),
    MEDIO(4,5,60),
    DIFICIL(5,6,80);
    
    public final int filas;
    public final int columnas;
    public final int tiempoInicial;

    private Dificultad(int filas, int columnas, int tiempoInicial) {
        this.filas = filas;
        this.columnas = columnas;
        this.tiempoInicial = tiempoInicial;
    }
    
    
    
}
