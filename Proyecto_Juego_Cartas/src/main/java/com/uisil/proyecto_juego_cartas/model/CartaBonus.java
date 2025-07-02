/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;

import com.uisil.proyecto_juego_cartas.logic.Juego;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Yordi
 */

public class CartaBonus extends Carta {

    public enum TipoBonus {
        MAS_CINCO_SEG, PUNTOS_DOBLES, COMODIN, MOSTRAR_PAREJA, MOSTRAR_FILA
    }

    private Juego juego;
    private TipoBonus tipo;

    public CartaBonus(int id, Juego juego, TipoBonus tipo) {
        super(id);
        this.juego = juego;
        this.tipo = tipo;
    }

    public void activarBonus() {
        switch (tipo) {
            case MAS_CINCO_SEG:
                juego.restarTiempo(-5);
                System.out.println("Se agregaron 5 segundos. Tiempo total: " + juego.getTiempoRestante());
                break;
            case PUNTOS_DOBLES:
                // Implementar lógica de puntos dobles si aplica
                System.out.println("¡Esta carta ahora vale doble al emparejarse!");
                break;
            case COMODIN:
                // Implementar lógica de comodín si aplica
                System.out.println("Esta carta es un comodín.");
                break;
            case MOSTRAR_PAREJA:
                // Implementar lógica de mostrar pareja si aplica
                System.out.println("Pareja descubierta (simulado)." );
                break;
            case MOSTRAR_FILA:
                // Implementar lógica de mostrar fila si aplica
                System.out.println("Fila revelada (simulado)." );
                break;
        }
    }

    public TipoBonus getTipo() {
        return tipo;
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

