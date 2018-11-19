/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author Labing I5
 */
public class Escuela {
    private int idEscuela;
    private String nombre;

    public Escuela(int idEscuela, String nombre) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


   public Escuela() {
    }
    

   
}
