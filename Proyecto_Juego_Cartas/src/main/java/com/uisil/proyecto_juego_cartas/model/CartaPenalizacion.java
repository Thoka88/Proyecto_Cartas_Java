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
public class CartaPenalizacion extends Carta {
    public enum TipoPenalizacion {
        MENOS_CINCO_SEG, MENOS_UN_PUNTO, MENOS_DIEZ_SEG, VER_UNA_CARTA, MENOS_TREINTA_SEG, MEZCLAR_CARTAS
    }
    private Juego juego;
    private TipoPenalizacion tipo;

    public CartaPenalizacion(int id, Juego juego, TipoPenalizacion tipo) {
        super(id);
        this.juego = juego;
        this.tipo = tipo;
    }

    public void activarPenalizacion() {
        switch (tipo) {
            case MENOS_CINCO_SEG:
                juego.restarTiempo(5);
                System.out.println("Se restaron 5 segundos al temporizador.");
                break;
            case MENOS_UN_PUNTO:
                juego.restarPuntos(1);
                System.out.println("Se restó 1 punto al jugador.");
                break;
            case MENOS_DIEZ_SEG:
                juego.restarTiempo(10);
                System.out.println("Se restaron 10 segundos al temporizador.");
                break;
            case VER_UNA_CARTA:
                juego.activarRestriccionUnaCarta(2);
                System.out.println("Solo se puede mostrar una carta por turno durante 2 turnos.");
                break;
            case MENOS_TREINTA_SEG:
                juego.restarTiempo(30);
                System.out.println("Se restaron 30 segundos al temporizador.");
                break;
            case MEZCLAR_CARTAS:
                juego.mezclarCartasNoEmparejadas();
                System.out.println("Las cartas no emparejadas han sido mezcladas.");
                break;
        }
    }

    public TipoPenalizacion getTipo() {
        return tipo;
    }
}
