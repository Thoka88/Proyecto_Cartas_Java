/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.controllers;

import com.uisil.proyecto_juego_cartas.logic.Dificultad;
import com.uisil.proyecto_juego_cartas.logic.Tablero;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private VBox contenedorJuego; // o AnchorPane / BorderPane, según tu diseño FXML

    private Tablero tableroManager;

    public void initialize() {
        // Inicializar por defecto con dificultad media
        cargarTablero(Dificultad.MEDIO);
    }

    private void cargarTablero(Dificultad dificultad) {
        tableroManager = new Tablero(dificultad);
        contenedorJuego.getChildren().clear();  // limpia el contenedor actual
        contenedorJuego.getChildren().add(tableroManager.getTablero());
    }

    // Puedes tener botones o menú para elegir dificultad
    @FXML
    private void onModoFacil() {
        cargarTablero(Dificultad.FACIL);
    }

    @FXML
    private void onModoMedio() {
        cargarTablero(Dificultad.MEDIO);
    }

    @FXML
    private void onModoDificil() {
        cargarTablero(Dificultad.DIFICIL);
    }
}
