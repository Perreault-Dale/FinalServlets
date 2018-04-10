/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.SimpleDateFormat;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Hibernate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;

/**
 *
 * @author Dale
 */
public class EmployeeControl {
    
    public static void saveRecords(List<Employee> es) {
        SessionFactory factory = null;
        try {
            factory = DbControl.setUp();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            es.stream().forEach((e) -> {
                session.save(e);
            });
        }
        catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.getTransaction().commit();
        session.close();
        System.out.println("Records stored successfully.");
        try {
            DbControl.tearDown(factory);
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateRecords(Employee e1) {
        SessionFactory factory = null;
        try {
            factory = DbControl.setUp();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            Employee e = (Employee) session.createQuery("FROM Employee E WHERE E.empNumber=" + e1.getEmpNumber()).uniqueResult();
            e.setFirstName(e1.getFirstName());
            e.setLastName(e1.getLastName());
            e.setTitle(e1.getTitle());
            e.setTeam_id(e1.getTeam_id());
            e.setHireDate(e1.getHireDate());
            session.save(e);
        }
        catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.getTransaction().commit();
        session.close();
        System.out.println("Records stored successfully.");
        try {
            DbControl.tearDown(factory);
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int deleteRecords(int id) {
        SessionFactory factory = null;
        int res = 0;
        try {
            factory = DbControl.setUp();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            res = session.createQuery("DELETE FROM Employee E WHERE E.empNumber=" + id).executeUpdate();
        }
        catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.getTransaction().commit();
        session.close();
        try {
            DbControl.tearDown(factory);
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public static List<Employee> getRecords() {
        SessionFactory factory = null;
        try {
            factory = DbControl.setUp();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = factory.openSession();
        session.beginTransaction();
        List<Employee> tl = session.createQuery("FROM Employee T ORDER BY T.empNumber").list();
        try {
            DbControl.tearDown(factory);
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tl;
    }
    
    public static Employee getRecords(int id) {
        SessionFactory factory = null;
        try {
            factory = DbControl.setUp();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = factory.openSession();
        session.beginTransaction();
        Employee e1 = (Employee) session.createQuery("FROM Employee WHERE empNumber = " + id).uniqueResult();
        try {
            DbControl.tearDown(factory);
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e1;
    }
    
    public static List<Employee> InitialLoad() {
        Employee[] ea = new Employee[3];
        ea[0] = new Employee("John","Billings","Chief Executive Officer",1,new Date(110,6,13));
        
        List<Employee> tl = Arrays.asList(ea);
        saveRecords(tl);
        return tl;
    }
    
    public static String formatHtmlList(List<Employee> tl) {
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Employees List</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <h1>Employees List</h1>\n" +
        "        <div>\n" +
        "        <a href=\"CreateEmployee\">Create Employee</a>\n" +
        "        <table>\n" +
        "        <th>First Name</th><th>Last Name</th><th>Title</th>\n";
        
        ListIterator<Employee> li = tl.listIterator();
        while (li.hasNext()) {
            Employee t = li.next();
            html += "        <tr><td>" + t.getFirstName() + "</td><td>" + 
                    t.getLastName() + "</td><td>" + 
                    t.getTitle() + "</td><td>" +
                    "<a href=\"EmployeeDetail?id=" + t.getEmpNumber() + "\">View Details</a></td><td>" + 
                    "<a href=\"UpdateEmployee?id=" + t.getEmpNumber() + "\">Update Employee</a></td><td>" + 
                    "<a href=\"DeleteEmployee?id=" + t.getEmpNumber() + "\">Delete Employee</a><td></tr>\n";
        }
        html += "        </table>\n" +
        "        </div>\n" +
        "    </body>\n" +
        "</html>";
        
        return html;
    }
    
    public static String formatHtmlSingle(Employee tl) {
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Employee Detail - " + tl.getFirstName() + " " + tl.getLastName() + "</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <h1>Employee Detail</h1>\n" +
        "        <div>\n" +
        "        <a href=\"CreateEmployee\">Create Employee</a>\n" +
        "        <p>First Name: " + tl.getFirstName() + "\n" +
        "        <p>Last Name: " + tl.getLastName() + "\n" + 
        "        <p>Title: " + tl.getTitle() + "\n" +
        "        <p>Hire Date: " + new SimpleDateFormat("MM/dd/yyyy").format(tl.getHireDate()) + "\n" +
        "        <p><a href=\"List?q=employee\">Back To List</a></td><td>" + 
        "        <p><a href=\"UpdateEmployee?id=" + tl.getEmpNumber() + "\">Update Employee</a></td><td>" + 
        "        <p><a href=\"DeleteEmployee?id=" + tl.getEmpNumber() + "\">Delete Employee</a><td></tr>\n" + 
        "        </div>\n" +
        "    </body>\n" +
        "</html>";
        
        return html;
    }
    
}
