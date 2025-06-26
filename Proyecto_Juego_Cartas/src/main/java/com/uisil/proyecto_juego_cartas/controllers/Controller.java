/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisil.proyecto_juego_cartas.controllers;

import javafx.stage.Stage;

public abstract class Controller {
    
    private Stage stage;
    private String accion;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public abstract void initialize();
    
}
