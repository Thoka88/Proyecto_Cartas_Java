/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uisil.proyecto_juego_cartas.model;

/**
 *
 * @author Thoka
 */
public abstract class Carta {
   private int id;
   private  boolean bocaArriba ;
   private boolean colocada;
   
   public Carta(int id){
   this.id = id;
   this.bocaArriba = false;
   this.colocada = false;
   }
   
   public void voltear(){
       bocaArriba = !bocaArriba;
   }
   
   public void colocar(){
       colocada = true;
   }

    public int getId() {
        return id;
    }

    public boolean isBocaArriba() {
        return bocaArriba;
    }

    public boolean isColocada() {
        return colocada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBocaArriba(boolean bocaArriba) {
        this.bocaArriba = bocaArriba;
    }

    public void setColocada(boolean colocada) {
        this.colocada = colocada;
    }
   
   
}
