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
    FACIL(3,3,30),
    MEDIO(4,4,45),
    DIFICIL(4,5,60);
    
    public final int filas;
    public final int columnas;
    public final int tiempoInicial;

    private Dificultad(int filas, int columnas, int tiempoInicial) {
        this.filas = filas;
        this.columnas = columnas;
        this.tiempoInicial = tiempoInicial;
    }
    
    
    
}
