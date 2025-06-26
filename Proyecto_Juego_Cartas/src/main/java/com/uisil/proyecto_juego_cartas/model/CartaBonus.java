/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;


/**
 *
 * @author Yordi
 */
public class CartaBonus {
    //general
    public void masCincoSeg(){
        tiempoRestante += 5; // Asumiendo que tienes una variable tiempoRestante en segundos
        System.out.println("Se agregaron 5 segundos. Tiempo total: " + tiempoRestante);
    }
    //general
    public void verCarta(Carta carta){
        carta.voltear(); // Supone que la clase Carta tiene un método para voltearse
        System.out.println("Carta mostrada: " + carta.getValor());
}   
    //general
    public void puntosDobles(Carta carta){
        carta.setDoblePuntos(true); // Supone que la clase Carta tiene una bandera para eso
        System.out.println("¡Esta carta ahora vale doble al emparejarse!");
    } 
    //medio
    public void cartaComodin(Carta carta){
        carta.setEsComodin(true); // Supone que existe ese atributo en la carta
        System.out.println("Esta carta es un comodín.");
    }
    
    //medio
    public void rastrearPareja(int fila, Carta carta){
        for (int col = 0; col < tablero[fila].length; col++) {
            if (tablero[fila][col].getValor().equals(carta.getValor()) && !tablero[fila][col].equals(carta)) {
                System.out.println("La pareja está en la misma fila.");
                return;
            }
        }
        System.out.println("La pareja NO está en la misma fila.");
    }

    //modo dificil
    public void mostrarPareja(){
        List<Carta> cartasVisibles = obtenerCartasNoEmparejadas();
        if (cartasVisibles.size() < 2) return;

        Collections.shuffle(cartasVisibles);
        Carta c1 = cartasVisibles.get(0);

        for (Carta c2 : cartasVisibles) {
            if (!c1.equals(c2) && c1.getValor().equals(c2.getValor())) {
                c1.voltear();
                c2.voltear();
                System.out.println("Pareja descubierta: " + c1.getValor());
                return;
            }
        }
    }
    //modo dificil
    public void mostrarFila(int fila){
        for (int col = 0; col < tablero[fila].length; col++) {
            tablero[fila][col].voltear();
        }
        System.out.println("Fila " + fila + " revelada.");
    }
    
}
