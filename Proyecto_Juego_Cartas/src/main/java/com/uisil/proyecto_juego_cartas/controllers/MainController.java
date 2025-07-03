/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.controllers;

import com.uisil.proyecto_juego_cartas.logic.CartaEstado;
import com.uisil.proyecto_juego_cartas.logic.Dificultad;
import com.uisil.proyecto_juego_cartas.logic.Juego;
import com.uisil.proyecto_juego_cartas.logic.PartidaGuardada;
import com.uisil.proyecto_juego_cartas.logic.Tablero;
import com.uisil.proyecto_juego_cartas.model.Carta;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uisil.proyecto_juego_cartas.model.CartaBonus;
import com.uisil.proyecto_juego_cartas.model.CartaPenalizacion;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;

public class MainController {
    private Juego juego; // Referencia al juego
    private Stage stage; // Para mostrar diálogos
    private Dificultad dificultad;


    @FXML
    private VBox contenedorJuego;

    @FXML
    private Label lblJugador;

    @FXML
    private Label lblTiempo;


    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnPausar;

    @FXML
    private Button btnReanudar;

    @FXML
    private Button btnGuardar;

    private Timeline temporizador;
    private boolean juegoPausado = false;
    private int tiempoRestante;
    private Tablero tablero;
    private String nombreJugador;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

   public void iniciarJuego(String nombre, Dificultad dificultad) {
        this.nombreJugador = nombre;
        this.juego = new Juego();
        this.dificultad = dificultad;

        lblJugador.setText("Jugador: " + nombre);
        tablero = new Tablero(dificultad, juego, this);
        contenedorJuego.getChildren().clear();
        contenedorJuego.getChildren().add(tablero.getTablero());
        iniciarTemporizador(dificultad);

        btnReanudar.setDisable(true);  // Por defecto, está deshabilitado

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


        juego.setTiempoRestante(tiempoRestante);


        lblTiempo.setText("Tiempo: " + tiempoRestante + "s");
        temporizador = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            if (!juegoPausado) {
                tiempoRestante--;
                juego.restarTiempo(1);

                lblTiempo.setText("Tiempo: " + juego.getTiempoRestante() + "s");

                if (tiempoRestante <= 0) {
                    temporizador.stop();
                    lblTiempo.setText("¡Tiempo agotado!");
                    tablero.setCartasHabilitadas(false);
                    btnPausar.setDisable(true);
                    btnReanudar.setDisable(true);
                    btnGuardar.setDisable(true);
                }

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
    @FXML
    private Label lblPuntaje; // Asegúrate de tenerlo en tu FXML también

    public void actualizarPuntaje(int puntos) {
        lblPuntaje.setText("Puntos: " + puntos);
}
    @FXML
private void guardarPartidaJson() {
    PartidaGuardada partida = new PartidaGuardada();
    partida.setNombreJugador(nombreJugador);
    partida.setTiempoRestante(tiempoRestante);
    partida.setMovimientos(tablero.getMovimientos());
    partida.setContadorBonus(tablero.getContadorBonus());
    partida.setContadorPenalizaciones(tablero.getContadorPenalizaciones());

    partida.setDificultad(dificultad); // ✅ Se guarda la dificultad actual
    partida.setPuntos(tablero.getPuntos()); // ✅ Guardamos los puntos actuales

    List<CartaEstado> estados = new ArrayList<>();
    for (Carta carta : tablero.getCartas()) {
        CartaEstado estado = new CartaEstado();
        estado.setId(carta.getId());
        estado.setColocada(carta.isColocada());
        estado.setBocaArriba(carta.isBocaArriba());
        estado.setEsBonus(carta instanceof CartaBonus);
        estado.setEsPenalizacion(carta instanceof CartaPenalizacion);
        estados.add(estado);
    }

    partida.setCartas(estados);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    FileChooser chooser = new FileChooser();
    chooser.setTitle("Guardar partida");
    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
    File archivo = chooser.showSaveDialog(btnGuardar.getScene().getWindow());

    if (archivo != null) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(partida, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    @FXML
private void cargarPartidaJson() {
    FileChooser chooser = new FileChooser();
    chooser.setTitle("Cargar partida");
    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
    File archivo = chooser.showOpenDialog(btnGuardar.getScene().getWindow());

    if (archivo != null) {
        try (FileReader reader = new FileReader(archivo)) {
            Gson gson = new Gson();
            PartidaGuardada partida = gson.fromJson(reader, PartidaGuardada.class);

            if (partida.getDificultad() == null) {
                mostrarMensaje("El archivo JSON no contiene la dificultad. No se puede cargar la partida.");
                return;
            }

            System.out.println("Dificultad cargada: " + partida.getDificultad());

            this.nombreJugador = partida.getNombreJugador();
            this.tiempoRestante = partida.getTiempoRestante();
            this.dificultad = partida.getDificultad(); // ✅ Recuperamos la dificultad

            lblJugador.setText("Jugador: " + nombreJugador);
            lblTiempo.setText("Tiempo: " + tiempoRestante + "s");

            this.juego = new Juego();
            juego.setTiempoRestante(tiempoRestante);

            tablero = new Tablero(dificultad, juego, this); // ✅ Se crea el tablero con la dificultad cargada
            tablero.reconstruirDesdeEstado(partida); // ✅ Restaura estado

            contenedorJuego.getChildren().clear();
            contenedorJuego.getChildren().add(tablero.getTablero());

            iniciarTemporizador(dificultad);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    @FXML
    private void pausarJuego() {
        juegoPausado = true;
        temporizador.pause();
        tablero.setCartasHabilitadas(false);
        btnPausar.setDisable(true);
        btnReanudar.setDisable(false);
    }

    @FXML
    private void reanudarJuego() {
        juegoPausado = false;
        temporizador.play();
        tablero.setCartasHabilitadas(true);
        btnPausar.setDisable(false);
        btnReanudar.setDisable(true);
    }
    
    

    @FXML
    private void guardarPartida() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Guardar partida");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));

    // ✅ Tomamos el Stage desde cualquier nodo (btnGuardar en este caso)
    File archivo = fileChooser.showSaveDialog(btnGuardar.getScene().getWindow());

    if (archivo != null) {
        StringBuilder datos = new StringBuilder();
        datos.append("Jugador: ").append(nombreJugador).append("\n");
        datos.append("Tiempo restante: ").append(tiempoRestante).append(" segundos\n");
        datos.append("Movimientos: ").append(tablero.getMovimientos()).append("\n");
        datos.append("Puntaje: ").append(tablero.getPuntos()).append("\n");
        datos.append("Bonus activados: ").append(tablero.getContadorBonus()).append("\n");
        datos.append("Penalizaciones recibidas: ").append(tablero.getContadorPenalizaciones()).append("\n");

        try {
            Files.write(archivo.toPath(), datos.toString().getBytes());
            System.out.println("Partida guardada en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Guardado cancelado.");
    }
}

}


