/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.controllers;

import com.uisil.proyecto_juego_cartas.logic.Dificultad;
import com.uisil.proyecto_juego_cartas.logic.Juego;
import com.uisil.proyecto_juego_cartas.logic.Tablero;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {
    private Juego juego;
    private Tablero tablero;
    private int tiempoRestante;
    private Timeline temporizador;

    @FXML
    private VBox contenedorJuego;

    @FXML
    private Label lblJugador;

    @FXML
    private Label lblTiempo;

    public void iniciarJuego(String nombre, Dificultad dificultad) {
        this.juego = new Juego();
        lblJugador.setText("Jugador: " + nombre);
        tablero = new Tablero(dificultad, juego);
        contenedorJuego.getChildren().clear();
        contenedorJuego.getChildren().add(tablero.getTablero());
        iniciarTemporizador(dificultad);
    }

    private void iniciarTemporizador(Dificultad dificultad) {
        switch (dificultad) {
            case FACIL:
                tiempoRestante = 50;
                break;
            case MEDIO:
                tiempoRestante = 60;
                break;
            case DIFICIL:
                tiempoRestante = 80;
                break;
            default:
                tiempoRestante = 60;
                break;
        }
        lblTiempo.setText("Tiempo: " + tiempoRestante + "s");
        temporizador = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoRestante--;
            lblTiempo.setText("Tiempo: " + tiempoRestante + "s");
            if (tiempoRestante <= 0) {
                temporizador.stop();
                lblTiempo.setText("¡Tiempo agotado!");
            }
        }));
        temporizador.setCycleCount(tiempoRestante);
        temporizador.play();
    }
}


