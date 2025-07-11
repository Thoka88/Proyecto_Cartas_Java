/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.utils;

import com.uisil.proyecto_juego_cartas.model.Carta;
import com.uisil.proyecto_juego_cartas.model.CartaBonus;
import com.uisil.proyecto_juego_cartas.model.CartaPenalizacion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class UIUtils {
    public static StackPane crearCeldaSoloVisual(Carta carta, Image imagenCarta, Image imagenReverso, Map<Carta, Button> mapa) {
        StackPane contenedor = new StackPane();
        Button boton = new Button();
        boton.setPrefSize(180, 180);
        boton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Imagen inicial = reverso
        boton.setStyle("-fx-background-image: url('" + imagenReverso.getUrl() + "'); " +
               "-fx-background-size: 80% 80%; " +
               "-fx-background-repeat: no-repeat; " +
               "-fx-background-position: center center; " +
               "-fx-border-color: black;");

        mapa.put(carta, boton);
        contenedor.getChildren().add(boton);
        StackPane.setAlignment(boton, Pos.CENTER);
        StackPane.setMargin(boton, new Insets(5));
        return contenedor;
    }
}
