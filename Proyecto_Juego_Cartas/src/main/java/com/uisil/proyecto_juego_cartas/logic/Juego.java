/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

import com.uisil.proyecto_juego_cartas.model.Carta;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

public class Juego {
    private int tiempoRestante; // en segundos
    private int puntajeJugador;
    private int turnosRestriccionUnaCarta;
    private Tablero tablero;
    private boolean puntosDoblesActivos = false;

    public Juego() {
        this.tiempoRestante = 60; // ejemplo de tiempo inicial
        this.puntajeJugador = 0;
        this.turnosRestriccionUnaCarta = 0;
    }

    public void restarTiempo(int segundos) {
        tiempoRestante -= segundos;
        if (tiempoRestante < 0) {
            tiempoRestante = 0;
        }
        System.out.println("Tiempo restante: " + tiempoRestante + " segundos");
    }

    public void restarPuntos(int puntos) {
        puntajeJugador -= puntos;
        if (puntajeJugador < 0) {
            puntajeJugador = 0;
        }
        System.out.println("Puntaje actual: " + puntajeJugador);
    }

    public void activarRestriccionUnaCarta(int turnos) {
        this.turnosRestriccionUnaCarta = turnos;
        System.out.println("Restricción de una carta por turno activada por " + turnos + " turnos.");
    }

    public void mezclarCartasNoEmparejadas() {
        List<Carta> noEmparejadas = getCartasNoEmparejadas();
        Collections.shuffle(noEmparejadas);
        System.out.println("Cartas no emparejadas mezcladas.");
        if (tablero != null) {
            tablero.mezclarCartasNoEmparejadasVisual(noEmparejadas);
        }
    }

    public List<Carta> getCartasNoEmparejadas() {
        if (tablero == null) return List.of();
        return tablero.getCartas().stream()
            .filter(c -> !c.isColocada())
            .collect(Collectors.toList());
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public int getPuntajeJugador() {
        return puntajeJugador;
    }

    public int getTurnosRestriccionUnaCarta() {
        return turnosRestriccionUnaCarta;
    }

    public boolean isPuntosDoblesActivos() {
        return puntosDoblesActivos;
    }

    public void activarPuntosDobles() {
        puntosDoblesActivos = true;
    }

    public void desactivarPuntosDobles() {
        puntosDoblesActivos = false;
    }

    public void emparejarCartas(Carta c1, Carta c2) {
        c1.colocar();
        c2.colocar();
    }
    
}

