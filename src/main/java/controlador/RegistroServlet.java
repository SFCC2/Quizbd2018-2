/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;/**/


import DAO.CursoDAO;
import DAO.EscuelaDAO;
import DAO.EstudianteDAO;
import DAO.ProfesorDAO;
import DAO.RegistroDAO;

import VO.Curso;
import VO.Escuela;
import VO.Estudiante;
import VO.Profesor;
import VO.Registro;


/**
 *
 * @author Labing I5
 */
public class RegistroServlet extends HttpServlet {
    private CursoDAO Cdao;
     private EscuelaDAO Ecdao;
     private EstudianteDAO ESsao;
     private ProfesorDAO Pdao;
     private RegistroDAO Rdao;

    public void init() throws ServletException {
         this.Cdao = new CursoDAO();
         this.Ecdao = new EscuelaDAO();
         this.ESsao = new EstudianteDAO();
         this.Pdao = new ProfesorDAO();
         this.Rdao = new RegistroDAO();
    }
    
  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
            if(request.getParameter("borrar")!= null){
                try {
                    String id = request.getParameter("borrar");
                    Estudiante persona = this.ESsao.find(Integer.parseInt(id));
                    this.ESsao.delete(persona);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(request.getParameter("editar")!=null){
                String id = request.getParameter("editar");
                Estudiante persona=null;
                try {
                    persona = this.ESsao.find(Integer.parseInt(id));
                } catch (SQLException ex) {
                    Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("persona", persona);
                
            }
            ArrayList<Estudiante> personas =(ArrayList) this.ESsao.findAll();
            request.setAttribute("lista", personas);
            rq.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getParameter("Boton1")!=null){
            System.out.println("Presiona el boton 1");
        }else{
          System.out.println("Presiona el boton21");
        }
        
//        String[] vehiculos = request.getParameterValues("vehicle");
//        for (String vehiculo : vehiculos) {
//            System.out.println(vehiculo);
//        }
       if(request.getParameter("editar")!=null){
          //Estoy editando XD
          //beca=null;            int  codEscuela=0;
          String cedula = request.getParameter("cedula");
          String nombre = request.getParameter("nombre");
          String codEscuela= request.getParameter("codEscuela");
          String beca = request.getParameter("beca");
          if(nombre != null && codEscuela != null && beca != null && nombre.length()>0){
              try {
                  Estudiante persona = this.ESsao.find(Integer.parseInt(cedula));
                  persona.setNombre(nombre);
                  persona.setCodeEscuela(codEscuela);
                   persona.setBeca(beca);
                  this.ESsao.update( persona);
              } catch (SQLException ex) {
                  Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          
       } else{
          //Estoy registrando uno 
          
         String cedula = request.getParameter("cedula");
          String nombre = request.getParameter("nombre");
          String codEscuela= request.getParameter("codEscuela");
          String beca = request.getParameter("beca");
          //Validaciones - SQL Inyection - Luego
           if(nombre != null && codEscuela != null && beca != null && nombre.length()>0){
              try {
                  Estudiante persona =
                          new Estudiante(Integer.parseInt(cedula), nombre, codEscuela,beca);
                  if(!this.ESsao.insert(persona)){
                      response.sendRedirect("index.jsp?error=ErrorDatos");
                  }  
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
           
          }
       }
       response.sendRedirect("RegistroServlet");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
