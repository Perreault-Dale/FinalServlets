/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.TeamControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Team;

/**
 *
 * @author Dale
 */
@WebServlet(name = "CreateTeam", urlPatterns = {"/CreateTeam"})
public class CreateTeam extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Create a New Team</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <h1>New Team</h1>\n" +
        "        <div>\n" +
        "        <form action=\"CreateTeam\" method=\"POST\">\n" +
        "           Team Name: <input type=\"text\" name=\"name\"><br>\n" + 
        "           Description: <input type=\"text\" name=\"desc\"><br>\n" + 
        "           <input type=\"submit\" value=\"Submit\">\n" +
        "        </form>\n" +
        "    </body>\n" +
        "</html>";
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(html);
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
        Team t1 = new Team();
        t1.setName(request.getParameter("name"));
        t1.setDesc(request.getParameter("desc"));
        List<Team> tl = new ArrayList<>();
        tl.add(t1);
        TeamControl.saveRecords(tl);
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Create a New Team</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta http-equiv=\"refresh\" content=\"2; URL=List?q=team\">" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        Team created successfully; redirecting to team list.\n" +
        "    </body>\n" +
        "</html>";
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(html);
        }
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
