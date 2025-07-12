module com.uisil.proyecto_juego_cartas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires com.google.gson;

    exports com.uisil.proyecto_juego_cartas;
    exports com.uisil.proyecto_juego_cartas.controllers;
    exports com.uisil.proyecto_juego_cartas.logic;
    exports com.uisil.proyecto_juego_cartas.model;

    opens com.uisil.proyecto_juego_cartas.controllers to javafx.fxml;
    opens com.uisil.proyecto_juego_cartas.logic to com.google.gson;
    opens com.uisil.proyecto_juego_cartas.model to com.google.gson;
}
