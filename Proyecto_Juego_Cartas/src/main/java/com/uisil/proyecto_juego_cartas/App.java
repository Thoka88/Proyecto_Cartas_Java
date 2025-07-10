package com.uisil.proyecto_juego_cartas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;  // guarda el stage principal por si lo necesitas luego

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/uisil/proyecto_juego_cartas/views/InicioMain.fxml"));
        Parent root = fxmlLoader.load();

        System.out.println(App.class.getResource("/com/uisil/proyecto_juego_cartas/estilos/Inicio.css"));

        Scene scene = new Scene(root, 720, 680); // ✅ declarar la variable
            // Cargar fuente personalizada
        javafx.scene.text.Font.loadFont(App.class.getResourceAsStream("/fonts/MinecraftRegular.oft"), 50);

        // Agregar hoja de estilo Minecraft
        scene.getStylesheets().add(App.class.getResource("/estilos/Styles.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Pantalla de Inicio");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}