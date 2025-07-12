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
import static com.uisil.proyecto_juego_cartas.logic.Dificultad.DIFICIL;
import static com.uisil.proyecto_juego_cartas.logic.Dificultad.FACIL;
import static com.uisil.proyecto_juego_cartas.logic.Dificultad.MEDIO;
import com.uisil.proyecto_juego_cartas.logic.Repeticion;
import com.uisil.proyecto_juego_cartas.model.CartaBonus;
import com.uisil.proyecto_juego_cartas.model.CartaPenalizacion;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.Arrays;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


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
    private Label lblAjuste;

    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnPausar;

    @FXML
    private Button btnReanudar;

    @FXML
    private Button btnInicio;
    
    @FXML
    private Button btnCargar;
    
    @FXML
    private Button btnGuardar;
    
    @FXML 
    private VBox panelAjustes;
    
    @FXML 
    private Button btnAjustes;
    
    @FXML 
    private Slider sliderVolumen;
    
    @FXML 
    private CheckBox chkMusica;
    
    @FXML 
    private BorderPane rootBorderPane;
    
    
    

    private Timeline temporizador;
    private boolean juegoPausado = false;
    private int tiempoRestante;
    private Tablero tablero;
    private String nombreJugador;
    private MediaPlayer mediaPlayer;

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
                    mostrarMensaje("El juego ha terminado");
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
    reproducirSonidoBoton();
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
    reproducirSonidoBoton();
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
    private void mostrarPanelAjustes() {
        reproducirSonidoBoton();
        panelAjustes.setVisible(true);
}

    @FXML
    private void ocultarPanelAjustes() {
        reproducirSonidoBoton();
        panelAjustes.setVisible(false);
}

    @FXML
    private void pausarJuego() {
        reproducirSonidoBoton();
        juegoPausado = true;
        temporizador.pause();
        tablero.setCartasHabilitadas(false);
        btnPausar.setDisable(true);
        btnReanudar.setDisable(false);
    }

    @FXML
    private void reanudarJuego() {
        reproducirSonidoBoton();
        juegoPausado = false;
        temporizador.play();
        tablero.setCartasHabilitadas(true);
        btnPausar.setDisable(false);
        btnReanudar.setDisable(true);
    }
    
    

    @FXML
    private void guardarPartida() {
        reproducirSonidoBoton();
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
    public Repeticion obtenerRepeticionGuardada() {
    return tablero.getUltimaRepeticion();
}

   

public VBox crearPanelAjustes() {
    if (panelAjustes != null) return panelAjustes;

    Slider sliderVolumen = new Slider(0, 100, 50);
    sliderVolumen.setShowTickLabels(true);
    sliderVolumen.setShowTickMarks(true);
    sliderVolumen.setMajorTickUnit(25);

    CheckBox chkMusica = new CheckBox("Activar Música");
    chkMusica.setSelected(true);

    Button btnCerrar = new Button("Cerrar Ajustes");
    btnCerrar.setOnAction(e -> panelAjustes.setVisible(false));

    panelAjustes = new VBox(15, sliderVolumen, chkMusica, btnCerrar);
    panelAjustes.setPadding(new Insets(20));
    panelAjustes.setStyle("-fx-background-color: rgba(0, 0, 0, 0.85); -fx-background-radius: 10;");
    panelAjustes.setVisible(false);
    panelAjustes.setMaxWidth(300);
    StackPane.setAlignment(panelAjustes, Pos.CENTER);

    return panelAjustes;
}
@FXML
public void initialize() {
    // Ruta al archivo de audio dentro de resources
    Font minecraftFont = Font.loadFont(getClass().getResource("/fonts/MinecraftRegular.otf").toExternalForm(), 30);
    lblJugador.setFont(new Font("Minecraft", 15));
    lblTiempo.setFont(new Font("Minecraft", 15));
    lblPuntaje.setFont(new Font("Minecraft", 15));
    lblMensaje.setFont(new Font("Minecraft", 15));
    btnAjustes.setFont(new Font("Minecraft", 15));
    btnCargar.setFont(new Font("Minecraft", 15));
    btnGuardar.setFont(new Font("Minecraft", 15));
    btnInicio.setFont(new Font("Minecraft", 15));
    btnPausar.setFont(new Font("Minecraft", 15));
    btnReanudar.setFont(new Font("Minecraft", 15));
    lblAjuste.setFont(minecraftFont); 
    lblAjuste.setTextFill(Color.WHITE);
    chkMusica.setFont(new Font("Minecraft", 18));
    chkMusica.setTextFill(Color.WHITE);
   
    String ruta = getClass().getResource("/audio/SoundTrack_Main.mp3").toExternalForm();
    Media media = new Media(ruta);
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Para que se repita
    mediaPlayer.play();

    // Configurar volumen desde el slider
    sliderVolumen.valueProperty().addListener((obs, oldVal, newVal) -> {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(newVal.doubleValue() / 100.0); // Valor entre 0 y 1
        }
    });

    // Música activada/desactivada
    chkMusica.selectedProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal) {
            mediaPlayer.play();
        } else {
            mediaPlayer.pause();
        }
    });
}
@FXML
public void salirJuego(){
    reproducirSonidoBoton();
System.exit(0);

}
private void reproducirSonidoBoton() {
    String rutaSonido = getClass().getResource("/audio/sonido_click.mp3").toExternalForm();
    AudioClip sonido = new AudioClip(rutaSonido);
    sonido.play();
}

    public void detenerMusica() {
        reproducirSonidoBoton();
    if (mediaPlayer != null) {
        mediaPlayer.stop(); // Detiene y libera recursos
    }
    }
    @FXML
private void volverAlInicio() {
    reproducirSonidoBoton();
    // Detener temporizador y música
    if (temporizador != null) {
        temporizador.stop();
    }
    detenerMusica();

    // Guardar repetición solo si hay movimientos
    if (tablero != null && !tablero.getMovimientosRealizados().isEmpty()) {
        // Crear copia defensiva de la estructura de cartas
        Carta[][] estructuraCopia = new Carta[tablero.getDificultad().filas][tablero.getDificultad().columnas];
        for (int i = 0; i < tablero.getEstructuraCartas().length; i++) {
            estructuraCopia[i] = Arrays.copyOf(tablero.getEstructuraCartas()[i], tablero.getEstructuraCartas()[i].length);
        }

        tablero.setUltimaRepeticion(new Repeticion(
            new ArrayList<>(tablero.getMovimientosRealizados()), // Copia de la lista
            estructuraCopia,
            tablero.getDificultad()
        ));
    }

    // Cargar vista de inicio
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_juego_cartas/views/InicioMain.fxml"));
        Parent root = loader.load();
        InicioMainController inicioController = loader.getController();
        
        // Pasar la repetición solo si existe
        if (tablero != null && tablero.getUltimaRepeticion() != null) {
            inicioController.setUltimaRepeticion(tablero.getUltimaRepeticion());
        }
        
        Stage stage = (Stage) btnInicio.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void verificarFinJuego() {
    boolean juegoTerminado = tablero.getCartas().stream().allMatch(Carta::isColocada) || tiempoRestante <= 0;
    
    if (juegoTerminado) {
        // Guardar repetición al finalizar el juego
        if (!tablero.getMovimientosRealizados().isEmpty()) {
            Carta[][] estructuraCopia = Arrays.stream(tablero.getEstructuraCartas())
                                       .map(Carta[]::clone)
                                       .toArray(Carta[][]::new);
            
            tablero.setUltimaRepeticion(new Repeticion(
                new ArrayList<>(tablero.getMovimientosRealizados()),
                estructuraCopia,
                tablero.getDificultad()
            ));
        }
        
        // Mostrar mensaje de fin de juego
        String mensaje = tiempoRestante <= 0 ? "¡Tiempo agotado!" : "¡Ganaste!";
        mostrarMensaje(mensaje);
        tablero.setCartasHabilitadas(false);
    }
}

public void iniciarModoRepeticion(Repeticion repeticion) {
    // Configuración inicial similar a iniciarJuego() pero para repetición
    this.juego = new Juego();
    this.dificultad = repeticion.getDificultad();
    
    lblJugador.setText("Reproduciendo partida...");
    lblTiempo.setText("");
    
    // Prepara el tablero
    tablero = new Tablero(dificultad, juego, this);
    contenedorJuego.getChildren().clear();
    contenedorJuego.getChildren().add(tablero.getTablero());
    
    // Desactiva controles innecesarios
    btnPausar.setDisable(true);
    btnReanudar.setDisable(true);
    btnGuardar.setDisable(true);
    
    // Botón para volver
    
    
    // Carga y reproduce la repetición
    tablero.reconstruirDesdeRepeticion(repeticion);
    tablero.reproducirRepeticionVisual();
}
}



