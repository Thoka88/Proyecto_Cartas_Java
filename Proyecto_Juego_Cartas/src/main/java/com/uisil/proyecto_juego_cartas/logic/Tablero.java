/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

import com.uisil.proyecto_juego_cartas.model.Carta;
import com.uisil.proyecto_juego_cartas.model.CartaNormal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {

    private final Dificultad dificultad;
    private final GridPane tablero;
    private final List<Carta> cartas;

    public Tablero(Dificultad dificultad) {
        this.dificultad = dificultad;
        this.cartas = generarCartas();
        this.tablero = construirTablero();
    }

    public GridPane getTablero() {
        return tablero;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    private List<Carta> generarCartas() {
        int totalCartas = dificultad.filas * dificultad.columnas;
        List<Carta> lista = new ArrayList<>();

        for (int i = 0; i < totalCartas / 2; i++) {
            Carta c1 = new CartaNormal(i);
            Carta c2 = new CartaNormal(i);
            lista.add(c1);
            lista.add(c2);
        }

        Collections.shuffle(lista);
        return lista;
    }

    private GridPane construirTablero() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        for (int i = 0; i < dificultad.columnas; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / dificultad.columnas);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int i = 0; i < dificultad.filas; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / dificultad.filas);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }

        int index = 0;
        for (int fila = 0; fila < dificultad.filas; fila++) {
            for (int col = 0; col < dificultad.columnas; col++) {
                Carta carta = cartas.get(index++);
                StackPane celda = crearCeldaResponsive(carta);
                grid.add(celda, col, fila);
            }
        }

        return grid;
    }

    private StackPane crearCeldaResponsive(Carta carta) {
        StackPane contenedor = new StackPane();
        contenedor.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");

        Button btn = new Button("?");
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn.setStyle("-fx-font-size: 2em;");

        btn.setOnAction(e -> btn.setText(String.valueOf(carta.getId())));

        contenedor.getChildren().add(btn);
        StackPane.setAlignment(btn, Pos.CENTER);
        StackPane.setMargin(btn, new Insets(5));

        contenedor.setPrefSize(100, 100);
        contenedor.setMinSize(10, 10);
        contenedor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        return contenedor;
    }
}
