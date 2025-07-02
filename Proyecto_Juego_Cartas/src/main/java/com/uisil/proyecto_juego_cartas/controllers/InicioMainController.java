/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.controllers;

import com.uisil.proyecto_juego_cartas.logic.Dificultad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InicioMainController {

    @FXML
    private TextField txtNombreJugador;

    @FXML
    private void iniciarFacil() {
        iniciarJuego(Dificultad.FACIL);
    }

    @FXML
    private void iniciarMedio() {
        iniciarJuego(Dificultad.MEDIO);
    }

    @FXML
    private void iniciarDificil() {
        iniciarJuego(Dificultad.DIFICIL);
    }

    private void iniciarJuego(Dificultad dificultad) {
        String nombre = txtNombreJugador.getText();
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Completa el campo de nombre.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_juego_cartas/views/Main.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.iniciarJuego(nombre, dificultad);
            Stage stage = (Stage) txtNombreJugador.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Juego de Memoria - " + nombre);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
