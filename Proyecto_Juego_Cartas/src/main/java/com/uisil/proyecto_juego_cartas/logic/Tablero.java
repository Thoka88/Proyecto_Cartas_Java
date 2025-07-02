/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.logic;

import com.uisil.proyecto_juego_cartas.model.Carta;
import com.uisil.proyecto_juego_cartas.model.CartaNormal;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {

    private final Dificultad dificultad;
    private final GridPane tablero;
    private final List<Carta> cartas;
    private final List<Image> imagenesCartas = new ArrayList<>();
    private final Image imagenReverso = new Image(getClass().getResource("/com/uisil/proyecto_juego_cartas/img/Signo_Interrogacion.png").toExternalForm());

    private final List<Button> botonesSeleccionados = new ArrayList<>();
    private final List<Carta> cartasSeleccionadas = new ArrayList<>();
    private boolean esperando = false;

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

            // Pre-cargar imagen solo una vez
            String ruta = ((CartaNormal) c1).getImagenRuta();
            Image img = new Image(getClass().getResource(ruta).toExternalForm());
            imagenesCartas.add(img); // el índice es el ID
        }

        Collections.shuffle(lista);
        return lista;
    }

    private GridPane construirTablero() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(1);
    grid.setVgap(1);

    for (int i = 0; i < dificultad.columnas; i++) {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(cc);
    }

    for (int i = 0; i < dificultad.filas; i++) {
        RowConstraints rc = new RowConstraints();
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

        Image imagenCarta = imagenesCartas.get(carta.getId());
        String imagenReversoURL = imagenReverso.getUrl();
        String imagenCartaURL = imagenCarta.getUrl();

        Button boton = new Button();
        boton.setPrefSize(180, 180);
        boton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        boton.setStyle("-fx-background-image: url('" + imagenReversoURL + "'); " +
               "-fx-background-size: 80% 80%; " +
               "-fx-background-repeat: no-repeat; " +
               "-fx-background-position: center center; " +
               "-fx-border-color: black;");

        boton.setOnAction(e -> {
            if (esperando || carta.isBocaArriba() || carta.isColocada()) return;

            carta.setBocaArriba(true);
            boton.setStyle("-fx-background-image: url('" + imagenCartaURL + "'); " +
               "-fx-background-size: 80% 80%; " +
               "-fx-background-repeat: no-repeat; " +
               "-fx-background-position: center center; " +
               "-fx-border-color: black;");
            cartasSeleccionadas.add(carta);
            botonesSeleccionados.add(boton);

            if (cartasSeleccionadas.size() == 2) {
                Carta c1 = cartasSeleccionadas.get(0);
                Carta c2 = cartasSeleccionadas.get(1);

                esperando = true;

                if (c1.getId() == c2.getId()) {
                    c1.colocar();
                    c2.colocar();
                    cartasSeleccionadas.clear();
                    botonesSeleccionados.clear();
                    esperando = false;
                } else {
                    PauseTransition pausa = new PauseTransition(Duration.seconds(0.3));
                    pausa.setOnFinished(ev -> {
                        c1.setBocaArriba(false);
                        c2.setBocaArriba(false);

                        for (Button b : botonesSeleccionados) {
                            b.setStyle("-fx-background-image: url('" + imagenReversoURL + "'); " +
                            "-fx-background-size: 80% 80%; " +
                            "-fx-background-repeat: no-repeat; " +
                            "-fx-background-position: center center; " +
                            "-fx-border-color: black;");
                        }

                        cartasSeleccionadas.clear();
                        botonesSeleccionados.clear();
                        esperando = false;
                    });
                    pausa.play();
                }
            }
        });

        contenedor.getChildren().add(boton);
        StackPane.setAlignment(boton, Pos.CENTER);
        StackPane.setMargin(boton, new Insets(5));

        return contenedor;
    }
}

