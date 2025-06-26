/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;
import com.uisil.proyecto_juego_cartas.logic.Juego;

/**
 *
 * @author Yordi
 */
public class CartaPenalizacion {
// Referencias necesarias (deberías conectarlas con el sistema principal del juego)
    private Juego juego;

    public CartaPenalizacion(Juego juego) {
        this.juego = juego;
    }

    // Fácil: Resta 5 segundos al timer
    public void menosCincoSeg() {
        juego.restarTiempo(5);
        System.out.println("Se restaron 5 segundos al temporizador.");
    }

    // Fácil: Resta 1 punto al puntaje
    public void menosUnPunto() {
        juego.restarPuntos(1);
        System.out.println("Se restó 1 punto al jugador.");
    }

    // Medio: Resta 10 segundos al timer
    public void menosDiezSeg() {
        juego.restarTiempo(10);
        System.out.println("Se restaron 10 segundos al temporizador.");
    }

    // Medio: Solo se puede ver una carta por turno durante 2 turnos
    public void verUnaCarta() {
        juego.activarRestriccionUnaCarta(2);
        System.out.println("Solo se puede mostrar una carta por turno durante 2 turnos.");
    }

    // Difícil: Resta 30 segundos al timer
    public void menosTreintaSeg() {
        juego.restarTiempo(30);
        System.out.println("Se restaron 30 segundos al temporizador.");
    }

    // Difícil: Mezclar cartas no emparejadas
    public void mezclarCartas() {
        juego.mezclarCartasNoEmparejadas();
        System.out.println("Las cartas no emparejadas han sido mezcladas.");
    }
}
