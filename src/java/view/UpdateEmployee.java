/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.EmployeeControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author Dale
 */
@WebServlet(name = "UpdateEmployee", urlPatterns = {"/UpdateEmployee"})
public class UpdateEmployee extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
        Employee e1 = EmployeeControl.getRecords(Integer.parseInt(request.getParameter("id")));
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Create a New Employee</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <h1>Update Employee</h1>\n" +
        "        <div>\n" +
        "        <form action=\"UpdateEmployee\" method=\"POST\">\n" +
        "           <input type=\"hidden\" name=\"id\" value=" + e1.getEmpNumber() + "><br>\n" + 
        "           First Name: <input type=\"text\" name=\"fn\" value=" + e1.getFirstName() + "><br>\n" + 
        "           Last Name: <input type=\"text\" name=\"ln\" value=" + e1.getLastName() + "><br>\n" +  
        "           Title: <input type=\"text\" name=\"title\" value=\"" + e1.getTitle() + "\"><br>\n" +  
        "           Team Number: <input type=\"text\" name=\"tn\" value=" + e1.getTeam_id()+ "><br>\n" +  
        "           Hire Date: <input type=\"text\" name=\"hire\" value=" + new SimpleDateFormat("MM/dd/yyyy").format(e1.getHireDate()) + "><br>\n" + 
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
        Employee e1 = new Employee();
        e1.setEmpNumber(Integer.parseInt(request.getParameter("id")));
        e1.setFirstName(request.getParameter("fn"));
        e1.setLastName(request.getParameter("ln"));
        e1.setTitle(request.getParameter("title"));
        e1.setTeam_id(Integer.parseInt(request.getParameter("tn")));
        try {
            e1.setHireDate(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("hire")));
        } catch (ParseException ex) {
            Logger.getLogger(CreateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            e1.setHireDate(new Date(100,1,1));
        }
        EmployeeControl.updateRecords(e1);
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Update an Existing Team</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta http-equiv=\"refresh\" content=\"2; URL=List?q=employee\">" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        Employee updated successfully; redirecting to employee list.\n" +
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
