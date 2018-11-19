/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author Asus-PC
 */
public class Registro {
    private int idRegistro;
    private String codEstudiante;
    private String codCurso;
    private String semestre;
    private String nota;

    public Registro(int idRegistro, String codEstudiante, String codCurso, String semestre, String nota) {
        this.idRegistro = idRegistro;
        this.codEstudiante = codEstudiante;
        this.codCurso = codCurso;
        this.semestre = semestre;
        this.nota = nota;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

  
    
       public Registro() {
    }
    
}
