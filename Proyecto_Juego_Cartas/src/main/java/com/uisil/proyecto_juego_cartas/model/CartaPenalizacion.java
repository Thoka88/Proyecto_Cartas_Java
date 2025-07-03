/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;
import com.uisil.proyecto_juego_cartas.logic.Juego;
import com.uisil.proyecto_juego_cartas.logic.Tablero;

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
        Tablero tablero = juego.getTablero();
        switch (tipo) {
            case MENOS_CINCO_SEG:
                juego.restarTiempo(5);
                if (tablero != null) tablero.mostrarMensajePenalizacion("¡Perdiste 5 segundos!");
                break;
            case MENOS_UN_PUNTO:
                juego.restarPuntos(1);
                if (tablero != null) tablero.mostrarMensajePenalizacion("¡Perdiste 1 punto!");
                break;
            case MENOS_DIEZ_SEG:
                juego.restarTiempo(10);
                if (tablero != null) tablero.mostrarMensajePenalizacion("¡Perdiste 10 segundos!");
                break;
            case VER_UNA_CARTA:
                juego.activarRestriccionUnaCarta(2);
                if (tablero != null) tablero.mostrarMensajePenalizacion("¡Solo puedes ver una carta por turno durante 2 turnos!");
                break;
            case MENOS_TREINTA_SEG:
                juego.restarTiempo(30);
                if (tablero != null) tablero.mostrarMensajePenalizacion("¡Perdiste 30 segundos!");
                break;
            case MEZCLAR_CARTAS:
                juego.mezclarCartasNoEmparejadas();
                if (tablero != null) {
                    tablero.mezclarCartasNoEmparejadasVisual(juego.getCartasNoEmparejadas());
                    tablero.mostrarMensajePenalizacion("¡Las cartas no emparejadas han sido mezcladas!");
                }
                break;
        }
    }

    public TipoPenalizacion getTipo() {
        return tipo;
    }
}
