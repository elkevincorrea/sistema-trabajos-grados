/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.entities.TrabajoGradoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Trabajo;

/**
 *
 * @author kecc
 */
public class ListTrabajosServlet extends HttpServlet {

    private TrabajoGradoJpaController trabajosCtlr;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.trabajosCtlr = new TrabajoGradoJpaController((EntityManagerFactory) getServletContext().getAttribute("emf"));
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
        List<Trabajo> trabajos;
        String director = request.getParameter("director");
        if(director != null && !"".equals(director)){
            trabajos = trabajosCtlr.findTrabajosByDirector(director);
        }else{
            trabajos = trabajosCtlr.findTrabajoEntities();
        }
        String ordenar = (String) request.getParameter("ordenarVencimiento");
        if(ordenar != null && !"".equals(ordenar)){
            trabajos.sort((Trabajo p1, Trabajo p2) -> {
                Date d = new Date(System.currentTimeMillis());
                int dif1 = p1.getFechaVencimiento() != null ? d.compareTo(p1.getFechaVencimiento()) : 0, dif2 = p2.getFechaVencimiento() != null ? d.compareTo(p2.getFechaVencimiento()) : 0;
                return dif1 - dif2;
            });
        }
        request.setAttribute("listTrabajos", trabajos);
        request.getRequestDispatcher("/comite/list-trabajos.jsp").forward(request, response);
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
