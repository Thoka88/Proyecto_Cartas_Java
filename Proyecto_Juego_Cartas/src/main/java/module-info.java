module com.uisil.proyecto_juego_cartas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.uisil.proyecto_juego_cartas to javafx.fxml;
    exports com.uisil.proyecto_juego_cartas;
}
