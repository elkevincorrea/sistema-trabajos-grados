/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.entities.EstudianteJpaController;
import controller.entities.PropuestaJpaController;
import controller.util.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Estudiante;
import models.Propuesta;

/**
 *
 * @author kecc
 */
public class PropuestaServlet extends HttpServlet {

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
            out.println("<title>Servlet PropuestaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PropuestaServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        try{
            int numEsts = Integer.parseInt(request.getParameter("numestudiantes"));
            List<Estudiante> estudiantes = new ArrayList<>(numEsts);
            for (int i = 1; i <= numEsts; i++) {
                int cod = Integer.parseInt(request.getParameter("codestudiante" + i));
                String name = request.getParameter("estudiante" + i);
                estudiantes.add(new Estudiante((long) cod, name));
            }
            String tematica = request.getParameter("tematica");
            int modalidad = Integer.parseInt(request.getParameter("modalidadesList"));
            Propuesta p = new Propuesta(tematica, estudiantes, modalidad);
            PropuestaJpaController controller = new PropuestaJpaController((EntityManagerFactory) getServletContext().getAttribute("emf"));
            controller.create(p);
            Part part = request.getPart("file-propuesta");
            String filePath = "propuesta_" + p.getId();
            filePath = Utils.uploadFile(filePath, part, LOGGER);
            if(filePath != null){
                p.setRutaPropuesta(filePath);
                controller.edit(p);
                LOGGER.log(Level.INFO, "Propuesta creada: {0}", p);
                request.setAttribute("title", "Propuesta agregada");
                request.setAttribute("message", "La propuesta ha sido agregada y se procedera a su revisión, el código de su propuesta es el siguiente " + p.getId());
                request.setAttribute("result", "Éxito");
            }else{
                controller.destroy(p.getId());
                request.setAttribute("title", "Error");
                request.setAttribute("message", "La propuesta no ha sido agregada");
                request.setAttribute("result", "Error :(");
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Problems creating propuesta. Error: {0}", e.getMessage());
        }
        request.getRequestDispatcher("/result-page.jsp").forward(request, response);
    }
    
    public String uploadFile(String fileName, Part part) throws IOException{
        String res = null;
        OutputStream out = null;
        InputStream fileContent = null;
        try {
            File f = new File(fileName);
            out = new FileOutputStream(f);
            fileContent = part.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = fileContent.read(bytes)) != -1){
                out.write(bytes, 0, read);
            }
            LOGGER.log(Level.INFO, "File uploaded at {0}",f.getAbsolutePath());
            res = f.getAbsolutePath();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", e.getMessage());
        } finally{
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
        return res;
    }
    
    private String getFileName(Part part){
        String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : partHeader.split(";")) {
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
