/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.controllers;

import com.uisil.proyecto_juego_cartas.logic.Dificultad;
import com.uisil.proyecto_juego_cartas.logic.Tablero;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {

    @FXML
    private VBox contenedorJuego;

    @FXML
    private Label lblJugador;

    @FXML
    private Label lblTiempo;

    private Timeline temporizador;
    private int tiempoRestante;

    public void iniciarJuego(String nombre, Dificultad dificultad) {
        lblJugador.setText("Jugador: " + nombre);
        cargarTablero(dificultad);
        iniciarTemporizador(dificultad);
    }

    private void cargarTablero(Dificultad dificultad) {
        Tablero tablero = new Tablero(dificultad);
        contenedorJuego.getChildren().clear();
        contenedorJuego.getChildren().add(tablero.getTablero());
    }

    private void iniciarTemporizador(Dificultad dificultad) {
        // ✅ Switch tradicional
        switch (dificultad) {
            case FACIL:
                tiempoRestante = 60;
                break;
            case MEDIO:
                tiempoRestante = 90;
                break;
            case DIFICIL:
                tiempoRestante = 120;
                break;
            default:
                tiempoRestante = 90;
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
