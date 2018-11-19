/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
 * @author Labing I5
 */
public class EstudianteDAO implements IBaseDatos<Estudiante>{
     public Estudiante find(int cedula) throws SQLException{
       Estudiante resultado = null;
       String query="Select * from Estudiante Where cedula ="+ cedula;
       Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, beca=null,  codEscuela=null;
	    if (rs.next()){
                resultado = new Estudiante();
	        id = rs.getInt("cedula");
	        resultado.setCedula(id);
	        nombre = rs.getString("nombre");
	        resultado.setNombre(nombre); 
	        codEscuela = rs.getString("codeEscuela");
                resultado.setCodeEscuela(codEscuela);
                beca = rs.getString("beca");
	        resultado.setBeca(beca);
                
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Estudiante");
			e.printStackTrace();
		}
	    return resultado;
    }
    
    
    @Override
    public List findAll() throws SQLException {
        	List<Estudiante> personas= null;
	    String query = "SELECT * FROM Estudiante";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, beca=null,  codEscuela=null;
	    while (rs.next()){
	    	if(personas == null){
	    		personas= new ArrayList<Estudiante>();
	    	}
	      
	        Estudiante registro= new Estudiante();
	        id = rs.getInt("cedula");
	        registro.setCedula(id);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre); 
                codEscuela = rs.getString("codEscuela");
	        registro.setCodeEscuela(codEscuela);                 
	        beca = rs.getString("beca");
                registro.setBeca(beca);
                
                
                personas.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return personas;
    }

    @Override
    public boolean insert(Estudiante persona) throws SQLException {
      	boolean result=false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Estudiante"  + " values (?,?,?,?)";
        PreparedStatement preparedStmt=null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getCedula());
            preparedStmt.setString(2, persona.getNombre());
            preparedStmt.setString(3, persona.getCodeEscuela());
            preparedStmt.setString(4, persona.getBeca());
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Estudiante persona) throws SQLException {
        boolean result=false; 
	Connection connection = Conexion.getConnection();
	String query = 
           "update Estudiante set Nombre = ?, CodeEscuela = ?, beca = ?  where cedula = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, persona.getNombre());
            preparedStmt.setString(2, persona.getCodeEscuela());
            preparedStmt.setString(3, persona.getBeca());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Estudiante persona) throws SQLException {
        boolean result=false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Estudiante where cedula = ?";
        System.out.println(query + " " + persona.getCedula());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getCedula());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }

    
}
