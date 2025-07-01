/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;

import com.uisil.proyecto_juego_cartas.logic.Juego;

import java.util.Collections;
import java.util.List;
/**
/**
 *
 * @author Yordi
 */
public class CartaBonus extends Carta {

    private Juego juego;

    public CartaBonus(Juego juego) {
        super(1); // Puedes usar otro id si lo necesitas diferenciar
        this.juego = juego;
    }

    // General: Agrega 5 segundos al timer
    public void masCincoSeg() {
        juego.restarTiempo(-5); // restar -5 equivale a sumar
        System.out.println("Se agregaron 5 segundos. Tiempo total: " + juego.getTiempoRestante());
    }

    // General: Voltea una carta
    public void verCarta(Carta carta) {
        carta.voltear();
        System.out.println("Carta mostrada: " + carta.getId());
    }

    // General: Esta carta vale doble
    public void puntosDobles(Carta carta) {
        if (carta instanceof CartaBonusExtensiones) {
            ((CartaBonusExtensiones) carta).setDoblePuntos(true);
            System.out.println("¡Esta carta ahora vale doble al emparejarse!");
        }
    }

    // Medio: Convierte carta en comodín
    public void cartaComodin(Carta carta) {
        if (carta instanceof CartaBonusExtensiones) {
            ((CartaBonusExtensiones) carta).setEsComodin(true);
            System.out.println("Esta carta es un comodín.");
        }
    }

    // Medio: Verifica si la pareja está en la misma fila (simulado)
    public void rastrearParejaEnFila(List<List<Carta>> tablero, int fila, Carta carta) {
        List<Carta> filaCartas = tablero.get(fila);
        for (Carta c : filaCartas) {
            if (!c.equals(carta) && c.getId() == carta.getId()) {
                System.out.println("La pareja está en la misma fila.");
                return;
            }
        }
        System.out.println("La pareja NO está en la misma fila.");
    }

    // Difícil: Voltea una pareja al azar
    public void mostrarPareja() {
        List<Carta> disponibles = juego.getCartasNoEmparejadas();
        if (disponibles.size() < 2) return;

        Collections.shuffle(disponibles);
        Carta c1 = disponibles.get(0);
        for (Carta c2 : disponibles) {
            if (!c1.equals(c2) && c1.getId() == c2.getId()) {
                c1.voltear();
                c2.voltear();
                System.out.println("Pareja descubierta: " + c1.getId());
                return;
            }
        }
    }

    // Difícil: Voltea todas las cartas de una fila
    public void mostrarFila(List<List<Carta>> tablero, int fila) {
        for (Carta carta : tablero.get(fila)) {
            carta.voltear();
        }
        System.out.println("Fila " + fila + " revelada.");
    }
}
