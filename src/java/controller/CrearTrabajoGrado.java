/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.entities.PropuestaJpaController;
import controller.entities.TrabajoGradoJpaController;
import controller.util.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Propuesta;
import models.Trabajo;

/**
 *
 * @author kecc
 */
public class CrearTrabajoGrado extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(PropuestaServlet.class.getCanonicalName());
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CrearTrabajoGrado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearTrabajoGrado at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String pId = request.getParameter("propuesta");
        PropuestaJpaController controller = new PropuestaJpaController((EntityManagerFactory) getServletContext().getAttribute("emf"));
        if(pId == null){
            List<Propuesta> propuestas = controller.findPropuestasAprobadas();
            request.setAttribute("propuestas", propuestas);
            request.getRequestDispatcher("/estudiantes/presentar-trabajo.jsp").forward(request, response);
        }else{
            Propuesta p = controller.findPropuesta(Long.parseLong(pId));
            request.setAttribute("propuesta", p);
            request.getRequestDispatcher("/estudiantes/presentar-trabajo.jsp").forward(request, response);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pId = request.getParameter("idpropuesta");
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        PropuestaJpaController controller = new PropuestaJpaController(emf);
        Propuesta propuesta;
        if(pId != null && (propuesta = controller.findPropuesta(Long.parseLong(pId))) != null){
            Part part = request.getPart("file-documento");
            String filePath = "documento_" + pId;
            filePath = Utils.uploadFile(filePath, part, LOGGER);
            if(filePath != null){
                Trabajo t = new Trabajo(propuesta);
                t.setRutaDocFinal(filePath);
                TrabajoGradoJpaController controllerTrabajos = new TrabajoGradoJpaController(emf);
                controllerTrabajos.create(t);
                request.setAttribute("title", "Documento Registrado");
                request.setAttribute("message", "El documento final de su trabajo ha sido registrado con fecha " + t.getFechaPresentacion().toString());
                request.setAttribute("result", "Éxito");
            }else{
                request.setAttribute("title", "Error");
                request.setAttribute("message", "Hubo un error con la carga del archivo");
                request.setAttribute("result", "Error :(");
            }
        }else{
            request.setAttribute("title", "Error");
            request.setAttribute("message", "No se encontro la propuesta...");
            request.setAttribute("result", "Error :(");
        }
        request.getRequestDispatcher("/result-page.jsp").forward(request, response);
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
