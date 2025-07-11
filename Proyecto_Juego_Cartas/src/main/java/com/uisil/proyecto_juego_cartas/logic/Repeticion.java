/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

import com.uisil.proyecto_juego_cartas.model.Carta;
import java.util.List;

/**
 *
 * @author Thoka
 */
public class Repeticion {
    private List<Movimiento> movimientos;
    private Carta[][] estructuraFinal;
    private Dificultad dificultad;

    public Repeticion(List<Movimiento> movimientos, Carta[][] estructuraFinal, Dificultad dificultad) {
        this.movimientos = movimientos;
        this.estructuraFinal = estructuraFinal;
        this.dificultad = dificultad;
    }

    // Getters
    public List<Movimiento> getMovimientos() { return movimientos; }
    public Carta[][] getEstructuraFinal() { return estructuraFinal; }
    public Dificultad getDificultad() { return dificultad; }
}
    

    // Getters
    

    

