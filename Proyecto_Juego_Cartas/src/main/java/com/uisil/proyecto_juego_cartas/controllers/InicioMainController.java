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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InicioMainController {

    @FXML
    private TextField txtNombreJugador;

    @FXML
    private ComboBox<String> cmbDificultad;

    @FXML
    private void iniciarJuego() {
        String nombre = txtNombreJugador.getText();
        String seleccion = cmbDificultad.getValue();

        if (nombre == null || nombre.isEmpty() || seleccion == null) {
            System.out.println("Completa todos los campos.");
            return;
        }

        try {
            Dificultad dificultad;

            // ✅ Switch tradicional
            switch (seleccion) {
                case "Fácil":
                    dificultad = Dificultad.FACIL;
                    break;
                case "Medio":
                    dificultad = Dificultad.MEDIO;
                    break;
                case "Difícil":
                    dificultad = Dificultad.DIFICIL;
                    break;
                default:
                    dificultad = Dificultad.MEDIO;
                    break;
            }

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
