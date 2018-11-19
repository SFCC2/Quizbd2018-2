/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.*;
import VO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Asus-PC
 */
public class RegistroDAO implements IBaseDatos<Registro>{
    public Registro find(int idRegistro) throws SQLException{
       Registro resultado = null;
       String query="Select * from Persona Where idRegistro ="+ idRegistro;
       Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String codEstudiante = null,   codCurso=null,semetre=null, nota=null;
	    if (rs.next()){
                resultado = new Registro();
	        id = rs.getInt("idRegistro");
	        resultado.setIdRegistro(id);
	        codEstudiante = rs.getString("codEstudiante");
	        resultado.setCodEstudiante(codEstudiante);
	        codCurso = rs.getString("codeRegistro");
                resultado.setCodCurso(codCurso);
                semetre = rs.getString("semetre");
	        resultado.setSemestre(semetre);
                nota = rs.getString("nota");
	        resultado.setNota(nota);
                
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Registro");
			e.printStackTrace();
		}
	    return resultado;
    }
    
    
    @Override
    public List findAll() throws SQLException {
        	List<Registro> registros= null;
	    String query = "SELECT * FROM Registro";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String codEstudiante = null,   codCurso=null,semetre=null, nota=null;
	    while (rs.next()){
	    	if(registros == null){
	    		registros= new ArrayList<Registro>();
	    	}
	      
	        Registro registro= new Registro();
	        id = rs.getInt("idRegistro");
	        registro.setIdRegistro(id);
	       codEstudiante = rs.getString("codEstudiante");
               
	        registro.setCodEstudiante(codEstudiante);
	        codCurso = rs.getString("codeRegistro");
                registro.setCodCurso(codCurso);
                semetre = rs.getString("semetre");
	        registro.setSemestre(semetre);
                nota = rs.getString("nota");
	        registro.setNota(nota);
                
                
                registros.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Registro");
			e.printStackTrace();
		}
	    
	    return registros;
    }

    @Override
    public boolean insert(Registro Registro) throws SQLException {
      	boolean result=false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Registro"  + " values (?,?,?,?,?)";
        PreparedStatement preparedStmt=null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, Registro.getIdRegistro());
            preparedStmt.setString(2, Registro.getCodEstudiante());
            preparedStmt.setString(3, Registro.getCodCurso());
            preparedStmt.setString(4, Registro.getSemestre());
            preparedStmt.setString(5, Registro.getNota());
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Registro registro) throws SQLException {
        boolean result=false; 
	Connection connection = Conexion.getConnection();
	String query = 
           "update Registro set codEstudiante = ?, apellido = ? where idRegistro = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, registro.getCodEstudiante());
            preparedStmt.setString(2, registro.getCodCurso());
            preparedStmt.setString(3, registro.getSemestre());
            preparedStmt.setString(4, registro.getNota());        
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Registro registro) throws SQLException {
        boolean result=false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Registro where idRegistro = ?";
        System.out.println(query + " " + registro.getIdRegistro());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, registro.getIdRegistro());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }

    
}