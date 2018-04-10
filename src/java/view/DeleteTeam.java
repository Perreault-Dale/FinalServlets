/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.TeamControl;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteTeam", urlPatterns = {"/DeleteTeam"})
public class DeleteTeam extends HttpServlet {

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
        Team t1 = TeamControl.getRecords(Integer.parseInt(request.getParameter("id")));
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Delete an Existing Team</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <h1>Delete Team</h1>\n" +
        "        <div>\n" +
        "        <form action=\"DeleteTeam\" method=\"POST\">\n" +
        "           <input type=\"hidden\" name=\"id\" value=" + t1.getId() + "><br>\n" + 
        "           Team Name: " + t1.getName() + "<br>\n" + 
        "           Description: " + t1.getDesc() + "<br>\n" + 
        "           <input type=\"submit\" value=\"Delete\">\n" +
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
        int res = TeamControl.deleteRecords(Integer.parseInt(request.getParameter("id")));
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Update an Existing Team</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta http-equiv=\"refresh\" content=\"2; URL=List?q=team\">" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        " + res + " team(s) deleted successfully; redirecting to team list.\n" +
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
