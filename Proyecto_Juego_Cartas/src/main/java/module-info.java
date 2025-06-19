module com.uisil.proyecto_juego_cartas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.uisil.proyecto_juego_cartas to javafx.fxml;
    exports com.uisil.proyecto_juego_cartas;
}
