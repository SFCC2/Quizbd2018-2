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
public class Estudiante {
    private int cedula;
    private String nombre;
    private String codeEscuela;
    private String beca;

    public Estudiante(int cedula, String nombre, String codeEscuela, String beca) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.codeEscuela = codeEscuela;
        this.beca = beca;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodeEscuela() {
        return codeEscuela;
    }

    public void setCodeEscuela(String codeEscuela) {
        this.codeEscuela = codeEscuela;
    }

    public String getBeca() {
        return beca;
    }

    public void setBeca(String beca) {
        this.beca = beca;
    }
    
       public Estudiante() {
    }
    
    
}