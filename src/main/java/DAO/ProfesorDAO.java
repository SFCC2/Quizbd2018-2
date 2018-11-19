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
public class ProfesorDAO implements IBaseDatos<Profesor>{
     public Profesor find(int cedula) throws SQLException{
       Profesor resultado = null;
       String query="Select * from Profesor Where cedula ="+ cedula;
       Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, apellido=null;
	    if (rs.next()){
                resultado = new Profesor();
	        id = rs.getInt("cedula");
	        resultado.setCedula(id);
	        nombre = rs.getString("nombre");
	        resultado.setNombre(nombre); 
                apellido = rs.getString("apellido");
	        resultado.setApellido(apellido);
                
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener profesor");
			e.printStackTrace();
		}
	    return resultado;
    }
    
    
    @Override
    public List findAll() throws SQLException {
        	List<Profesor> personas= null;
	    String query = "SELECT * FROM Profesor";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, apellido=null,  codEscuela=null;
	    while (rs.next()){
	    	if(personas == null){
	    		personas= new ArrayList<Profesor>();
	    	}
	      
	        Profesor registro= new Profesor();
	        id = rs.getInt("cedula");
	        registro.setCedula(id);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre);               
	        apellido = rs.getString("apellido");
                registro.setApellido(apellido);
                
                
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
    public boolean insert(Profesor persona) throws SQLException {
      	boolean result=false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Profesor"  + " values (?,?,?)";
        PreparedStatement preparedStmt=null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getCedula());
            preparedStmt.setString(2, persona.getNombre());
            preparedStmt.setString(3, persona.getApellido());
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Profesor persona) throws SQLException {
        boolean result=false; 
	Connection connection = Conexion.getConnection();
	String query = 
           "update Profesor set nombre = ?, apellido = ? where cedula = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, persona.getNombre());
            preparedStmt.setString(3, persona.getApellido());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Profesor persona) throws SQLException {
        boolean result=false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Profesor where cedula = ?";
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