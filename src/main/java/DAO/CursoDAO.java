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
 * @author Labing I5
 */
public class CursoDAO implements IBaseDatos<Curso>{
     public Curso find(int idCurso) throws SQLException{
       Curso resultado = null;
       String query="Select * from Curso Where idCurso ="+ idCurso;
       Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, codProfesor=null;
	    if (rs.next()){
                resultado = new Curso();
	        id = rs.getInt("idCurso");
	        resultado.setIdCurso(id);
	        nombre = rs.getString("nombre");
	        resultado.setNombre(nombre); 
	        codProfesor = rs.getString("codProfesor");
                resultado.setCodProfesor(codProfesor);
                
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Curso");
			e.printStackTrace();
		}
	    return resultado;
    }
    
    
    @Override
    public List findAll() throws SQLException {
        	List<Curso> cursos= null;
	    String query = "SELECT * FROM Curso";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, codProfesor=null;
	    while (rs.next()){
	    	if(cursos == null){
	    		cursos= new ArrayList<Curso>();
	    	}
	      
	        Curso registro= new Curso();
	        id = rs.getInt("idCurso");
	        registro.setIdCurso(id);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre); 
                codProfesor = rs.getString("codCurso");
	        registro.setCodProfesor(codProfesor);  
                
                
                cursos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return cursos;
    }

    @Override
    public boolean insert(Curso curso) throws SQLException {
      	boolean result=false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Curso"  + " values (?,?,?)";
        PreparedStatement preparedStmt=null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, curso.getIdCurso());
            preparedStmt.setString(2, curso.getNombre());
            preparedStmt.setString(3, curso.getCodProfesor());
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Curso curso) throws SQLException {
        boolean result=false; 
	Connection connection = Conexion.getConnection();
	String query = 
           "update Curso set nombre = ?, CodProfesor = ? where idCurso = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, curso.getNombre());
            preparedStmt.setString(2, curso.getCodProfesor());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Curso curso) throws SQLException {
        boolean result=false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Curso where idCurso = ?";
        System.out.println(query + " " + curso.getIdCurso());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, curso.getIdCurso());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }

    
}