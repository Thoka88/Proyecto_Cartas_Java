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
import com.uisil.proyecto_juego_cartas.logic.Juego;

public class MainController {
    private Juego juego; // agregamos esta referencia

    @FXML
    private VBox contenedorJuego;

    @FXML
    private Label lblJugador;

    @FXML
    private Label lblTiempo;

    @FXML
    private Label lblMensaje;

    private Timeline temporizador;
    private int tiempoRestante;

    public void iniciarJuego(String nombre, Dificultad dificultad) {
        lblJugador.setText("Jugador: " + nombre);

        juego = new Juego(); // crear nueva instancia de juego
        cargarTablero(dificultad);
        iniciarTemporizador(dificultad);
    }

    private void cargarTablero(Dificultad dificultad) {
        Tablero tablero = new Tablero(dificultad, juego, this); // pasar referencia a MainController
        contenedorJuego.getChildren().clear();
        contenedorJuego.getChildren().add(tablero.getTablero());
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
            juego.restarTiempo(1); // actualizar tiempo en juego también (para mantener sincronía)

            lblTiempo.setText("Tiempo: " + juego.getTiempoRestante() + "s");

            if (tiempoRestante <= 0) {
                temporizador.stop();
                lblTiempo.setText("¡Tiempo agotado!");
            }
        }));

        temporizador.setCycleCount(tiempoRestante);
        temporizador.play();
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
        lblMensaje.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> lblMensaje.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
