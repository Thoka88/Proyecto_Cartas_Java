/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;

import com.uisil.proyecto_juego_cartas.logic.Juego;
import com.uisil.proyecto_juego_cartas.logic.Tablero;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Yordi
 */

public class CartaBonus extends Carta {

    public enum TipoBonus {
        MAS_CINCO_SEG, PUNTOS_DOBLES,MOSTRAR_PAREJA, MOSTRAR_TRESPAREJAS
    }

    private Juego juego;
    private TipoBonus tipo;

    public CartaBonus(int id, Juego juego, TipoBonus tipo) {
        super(id);
        this.juego = juego;
        this.tipo = tipo;
    }

    public void activarBonus() {
        Tablero tablero = juego.getTablero();
        switch (tipo) {
            case MAS_CINCO_SEG:
                juego.restarTiempo(-5);
                if (tablero != null) tablero.mostrarMensajeBonus("¡Sumaste 5 segundos!");
                break;
            case PUNTOS_DOBLES:
                juego.activarPuntosDobles();
                if (tablero != null) tablero.mostrarMensajeBonus("¡Tus puntos valen doble!");
                break;
           case MOSTRAR_PAREJA:
    if (tablero != null) {
        
        tablero.mostrarParejaTemporal();
    }
    break;

            case MOSTRAR_TRESPAREJAS:
                // Revelar hasta 3 parejas al azar que no hayan sido encontradas
                List<Carta> noEmparejadas = new ArrayList<>(juego.getCartasNoEmparejadas());
                Collections.shuffle(noEmparejadas);
                int parejasMostradas = 0;
                List<Carta> usadas = new ArrayList<>();
                for (int i = 0; i < noEmparejadas.size() && parejasMostradas < 3; i++) {
                    Carta c1 = noEmparejadas.get(i);
                    if (usadas.contains(c1)) continue;
                    for (int j = i + 1; j < noEmparejadas.size(); j++) {
                        Carta c2 = noEmparejadas.get(j);
                        if (usadas.contains(c2)) continue;
                        if (c1.getId() == c2.getId()) {
                            c1.setBocaArriba(true);
                            c2.setBocaArriba(true);
                            if (tablero != null) {
                                tablero.voltearVisualmenteCarta(c1, true);
                                tablero.voltearVisualmenteCarta(c2, true);
                            }
                            juego.emparejarCartas(c1, c2);
                            usadas.add(c1);
                            usadas.add(c2);
                            parejasMostradas++;
                            break;
                        }
                    }
                }
                if (tablero != null) tablero.mostrarMensajeBonus("¡Se revelaron " + parejasMostradas + " parejas al azar!");
                break;
        }
    }

    public TipoBonus getTipo() {
        return tipo;
    }

    

    // General: Esta carta vale doble
    public void puntosDobles(Carta carta) {
        if (carta instanceof CartaBonusExtensiones) {
            ((CartaBonusExtensiones) carta).setDoblePuntos(true);
            System.out.println("¡Esta carta ahora vale doble al emparejarse!");
        }
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

   

}

