/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.controllers;

import com.uisil.proyecto_juego_cartas.logic.Dificultad;
import com.uisil.proyecto_juego_cartas.logic.Juego;
import com.uisil.proyecto_juego_cartas.logic.Repeticion;
import com.uisil.proyecto_juego_cartas.logic.Tablero;
import com.uisil.proyecto_juego_cartas.model.Carta;
import com.uisil.proyecto_juego_cartas.model.CartaNormal;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip;

public class InicioMainController {
       private MediaPlayer mediaPlayer;
       private Tablero tablero;
       private Repeticion ultimaRepeticion;
       private Tablero tableroRepeticion;

    @FXML
    private TextField txtNombreJugador;
    
    @FXML
    private Label lblMensaje;
    
    @FXML
    private Label lblTitulo;
    
    @FXML
    private Label lblDificultad;
    
    @FXML
    private Label lblAjuste;
    
     @FXML
    private Button btnFacil;
     
      @FXML
    private Button btnMedio;
      
       @FXML
    private Button btnDificil;
       
       @FXML
    private Button btnRepeticiones;
       
       @FXML
    private Button btnAjustes;
       
       @FXML
    private Button btnSalir;
       
        @FXML
    private Button btnCerrar;
    

    @FXML
    public void iniciarFacil() {
        reproducirSonidoBoton();
        iniciarJuego(Dificultad.FACIL);
    }

    @FXML
    public void iniciarMedio() {
        reproducirSonidoBoton();
        iniciarJuego(Dificultad.MEDIO);
    }

    @FXML
    public void iniciarDificil() {
        reproducirSonidoBoton();
        iniciarJuego(Dificultad.DIFICIL);
    }
    @FXML 
    private VBox panelAjustes;
    
    @FXML 
    private Slider sliderVolumen;
    
    @FXML 
    private CheckBox chkMusica;
    
    @FXML
    private AnchorPane rootAnchorPane;
    
    
    
    public void detenerMusica() {
    if (mediaPlayer != null) {
        mediaPlayer.stop(); // Detiene y libera recursos
    }
    }

    private void iniciarJuego(Dificultad dificultad) {
        detenerMusica();
        String nombre = txtNombreJugador.getText();
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarMensaje("Por favor, completa el campo de nombre.");
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
    public void setUltimaRepeticion(Repeticion rep) {
    this.ultimaRepeticion = rep;
}
    public void guardarRepeticionDesdeTablero(Tablero tablero) {
    Repeticion rep = new Repeticion(tablero.getMovimientosRealizados(), tablero.getEstructuraCartas(), tablero.getDificultad());
    setUltimaRepeticion(rep);
}
    
    

private void reproducirSonidoBoton() {
    String rutaSonido = getClass().getResource("/audio/sonido_click.mp3").toExternalForm();
    AudioClip sonido = new AudioClip(rutaSonido);
    sonido.play();
}
    
    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
        lblMensaje.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> lblMensaje.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
   
@FXML
private void mostrarPanelAjustes() {
     reproducirSonidoBoton();
    centrarPanel();
    panelAjustes.toFront(); // Asegura que esté al frente
    panelAjustes.setVisible(true);
}

@FXML
private void ocultarPanelAjustes() {
     reproducirSonidoBoton();
    panelAjustes.setVisible(false);
}

@FXML
public void salirJuego() {
    reproducirSonidoBoton();

    PauseTransition delay = new PauseTransition(Duration.millis(30));
    delay.setOnFinished(e -> System.exit(0));
    delay.play();
}

@FXML
private void verRepeticion() {
    if (ultimaRepeticion == null) {
        mostrarMensaje("No hay repetición disponible");
        return;
    }

    try {
        // Carga la misma vista del juego normal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uisil/proyecto_juego_cartas/views/Main.fxml"));
        Parent root = loader.load();
        
        // Obtiene el controlador del juego
        MainController mainController = loader.getController();
        
        // Configura el tablero en modo repetición
        mainController.iniciarModoRepeticion(ultimaRepeticion);
        
        // Muestra la escena
        Stage stage = (Stage) btnRepeticiones.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        detenerMusica();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}



@FXML
public void initialize() {
    // Ruta al archivo de audio dentro de resources
    Font minecraftFont = Font.loadFont(getClass().getResource("/fonts/MinecraftRegular.otf").toExternalForm(), 30);
    lblTitulo.setFont(new Font("Minecraft", 30));
    lblMensaje.setFont(new Font("Minecraft", 30));
    lblDificultad.setFont(new Font("Minecraft", 25));
    lblAjuste.setFont(minecraftFont); 
    lblAjuste.setTextFill(Color.WHITE);
    btnFacil.setFont(new Font("Minecraft", 25));
    btnMedio.setFont(new Font("Minecraft", 25));
    btnDificil.setFont(new Font("Minecraft", 25));
    btnAjustes.setFont(new Font("Minecraft", 23));
    btnSalir.setFont(new Font("Minecraft", 25));
    btnCerrar.setFont(new Font("Minecraft", 18));
    chkMusica.setFont(new Font("Minecraft", 18));
    chkMusica.setTextFill(Color.WHITE);
    btnRepeticiones.setFont(new Font("Minecraft", 18));
    txtNombreJugador.setFont(new Font("Minecraft", 18));
    
    String ruta = getClass().getResource("/audio/SoundTrack_InicioMain.mp3").toExternalForm();
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
    panelAjustes.setStyle("-fx-background-color: rgba(255, 0, 0, 0); -fx-background-radius: 10;");
    panelAjustes.setVisible(false);
    panelAjustes.setMaxWidth(300);
    StackPane.setAlignment(panelAjustes, Pos.CENTER);

    return panelAjustes;
}
private void centrarPanel() {
    double anchorWidth = rootAnchorPane.getWidth();
    double anchorHeight = rootAnchorPane.getHeight();

    double panelWidth = panelAjustes.getPrefWidth();
    double panelHeight = panelAjustes.getPrefHeight();

    panelAjustes.setLayoutX((anchorWidth - panelWidth) / 2);
    panelAjustes.setLayoutY((anchorHeight - panelHeight) / 2);
}
}
