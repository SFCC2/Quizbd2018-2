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
public class EscuelaDAO implements IBaseDatos<Escuela>{
     public Escuela find(int idEscuela) throws SQLException{
       Escuela resultado = null;
       String query="Select * from Escuela Where idEscuela ="+ idEscuela;
       Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, apellido=null,  codEscuela=null;
	    if (rs.next()){
                resultado = new Escuela();
	        id = rs.getInt("idEscuela");
	        resultado.setIdEscuela(id);
	        nombre = rs.getString("nombre");
	        resultado.setNombre(nombre); 
                
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Escuela");
			e.printStackTrace();
		}
	    return resultado;
    }
    
    
    @Override
    public List findAll() throws SQLException {
        	List<Escuela> personas= null;
	    String query = "SELECT * FROM Escuela";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, apellido=null,  codEscuela=null;
	    while (rs.next()){
	    	if(personas == null){
	    		personas= new ArrayList<Escuela>();
	    	}
	      
	        Escuela registro= new Escuela();
	        id = rs.getInt("idEscuela");
	        registro.setIdEscuela(id);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre); 
                
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
    public boolean insert(Escuela persona) throws SQLException {
      	boolean result=false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Escuela"  + " values (?,?,?)";
        PreparedStatement preparedStmt=null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getIdEscuela());
            preparedStmt.setString(2, persona.getNombre());
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Escuela persona) throws SQLException {
        boolean result=false; 
	Connection connection = Conexion.getConnection();
	String query = 
           "update Escuela set nombre = ?,  where idEscuela = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, persona.getNombre());;
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Escuela persona) throws SQLException {
        boolean result=false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Escuela where idEscuela = ?";
        System.out.println(query + " " + persona.getIdEscuela());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getIdEscuela());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }

    
}