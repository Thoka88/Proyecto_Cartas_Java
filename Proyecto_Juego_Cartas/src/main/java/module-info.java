module com.uisil.proyecto_juego_cartas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.base;
     requires javafx.media;

    opens com.uisil.proyecto_juego_cartas to javafx.fxml;
    opens com.uisil.proyecto_juego_cartas.controllers to javafx.fxml;
    opens com.uisil.proyecto_juego_cartas.logic to com.google.gson;
    opens com.uisil.proyecto_juego_cartas.model to com.google.gson;
    
    exports com.uisil.proyecto_juego_cartas;
    requires com.google.gson;
    requires javafx.mediaEmpty;
    
}
