/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GuardarPartida {

    private Stage stage;

    public GuardarPartida(Stage stage) {
        this.stage = stage;
    }

    public void guardarPartida(String contenido) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar partida");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivo de texto", "*.txt")
        );

        File archivo = fileChooser.showSaveDialog(stage);

        if (archivo != null) {
            if (!archivo.getName().toLowerCase().endsWith(".txt")) {
                archivo = new File(archivo.getParentFile(), archivo.getName() + ".txt");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                writer.write(contenido);
                System.out.println("Partida guardada en: " + archivo.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                // Aquí podrías mostrar una alerta de error si quieres
            }
        } else {
            System.out.println("Guardado cancelado por el usuario.");
        }
    }
}
