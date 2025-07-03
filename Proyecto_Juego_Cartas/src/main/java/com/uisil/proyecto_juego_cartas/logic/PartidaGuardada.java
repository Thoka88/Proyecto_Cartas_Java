/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

import java.util.List;

public class PartidaGuardada {
    private String nombreJugador;
    private int tiempoRestante;
    private int movimientos;
    private int contadorBonus;
    private int contadorPenalizaciones;
    private int puntos;
    private Dificultad dificultad;
    private List<CartaEstado> cartas;

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public int getContadorBonus() {
        return contadorBonus;
    }

    public void setContadorBonus(int contadorBonus) {
        this.contadorBonus = contadorBonus;
    }

    public int getContadorPenalizaciones() {
        return contadorPenalizaciones;
    }

    public void setContadorPenalizaciones(int contadorPenalizaciones) {
        this.contadorPenalizaciones = contadorPenalizaciones;
    }

    public List<CartaEstado> getCartas() {
        return cartas;
    }

    public void setCartas(List<CartaEstado> cartas) {
        this.cartas = cartas;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
