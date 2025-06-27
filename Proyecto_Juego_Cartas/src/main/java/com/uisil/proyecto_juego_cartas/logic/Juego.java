/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

import com.uisil.proyecto_juego_cartas.model.Carta;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Yordi
 */
public class Juego {
    private int tiempoRestante; // en segundos
    private int puntajeJugador;
    private int turnosRestriccionUnaCarta;
    private List<Carta> cartasNoEmparejadas;

    public Juego() {
        this.tiempoRestante = 60; // ejemplo de tiempo inicial
        this.puntajeJugador = 0;
        this.turnosRestriccionUnaCarta = 0;
        this.cartasNoEmparejadas = new ArrayList<>();
        // aquí podrías inicializar cartasNoEmparejadas con cartas
    }

    // Resta tiempo en segundos al temporizador, no menor a 0
    public void restarTiempo(int segundos) {
        tiempoRestante -= segundos;
        if (tiempoRestante < 0) {
            tiempoRestante = 0;
        }
        System.out.println("Tiempo restante: " + tiempoRestante + " segundos");
    }

    // Resta puntos, no menor a 0
    public void restarPuntos(int puntos) {
        puntajeJugador -= puntos;
        if (puntajeJugador < 0) {
            puntajeJugador = 0;
        }
        System.out.println("Puntaje actual: " + puntajeJugador);
    }

    // Activa la restricción para mostrar solo una carta por turno durante n turnos
    public void activarRestriccionUnaCarta(int turnos) {
        this.turnosRestriccionUnaCarta = turnos;
        System.out.println("Restricción de una carta por turno activada por " + turnos + " turnos.");
    }

    // Mezcla las cartas no emparejadas
    public void mezclarCartasNoEmparejadas() {
        Collections.shuffle(cartasNoEmparejadas);
        System.out.println("Cartas no emparejadas mezcladas.");
    }

    // Getters para pruebas o para otras clases
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getPuntajeJugador() {
        return puntajeJugador;
    }

    public int getTurnosRestriccionUnaCarta() {
        return turnosRestriccionUnaCarta;
    }

    public List<Carta> getCartasNoEmparejadas() {
        return cartasNoEmparejadas;
    }

    // Aquí podrías agregar métodos para manejar el paso de turnos y disminuir turnosRestriccionUnaCarta, etc.
}
