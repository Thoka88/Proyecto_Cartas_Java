module com.uisil.proyecto_juego_cartas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.base;

    opens com.uisil.proyecto_juego_cartas to javafx.fxml;
    opens com.uisil.proyecto_juego_cartas.controllers to javafx.fxml;
    exports com.uisil.proyecto_juego_cartas;
}
