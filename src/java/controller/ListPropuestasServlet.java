/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.entities.PropuestaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Propuesta;

/**
 *
 * @author kecc
 */
public class ListPropuestasServlet extends HttpServlet {

    private PropuestaJpaController propuestaController;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        propuestaController = new PropuestaJpaController((EntityManagerFactory) getServletContext().getAttribute("emf"));
    }

    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Propuesta> propuestas = null;
        int estado;
        try{
            estado = request.getParameter("estado") != null ? Integer.parseInt(request.getParameter("estado")) : -1;
        }catch (Exception e){
            estado = -1;
        }
        if(estado != -1){
            propuestas = propuestaController.findPropuestasByEstado(estado);
        }else{
            propuestas = propuestaController.findPropuestaEntities();
        }
        String ordenar = (String) request.getParameter("ordenarVencimiento");
        if(ordenar != null && ordenar != ""){
            propuestas.sort((Propuesta p1, Propuesta p2) -> {
                Date d = new Date(System.currentTimeMillis());
                int dif1 = p1.getFechaVencimiento() != null ? d.compareTo(p1.getFechaVencimiento()) : 0, dif2 = p2.getFechaVencimiento() != null ? d.compareTo(p2.getFechaVencimiento()) : 0;
                return dif1 - dif2;
            });
        }
        request.setAttribute("listPropuestas", propuestas);
        request.getRequestDispatcher("/comite/list-propuestas.jsp").forward(request, response);
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
