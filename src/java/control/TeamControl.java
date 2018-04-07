/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Team;
import org.hibernate.Hibernate;

/**
 *
 * @author Dale
 */
public class TeamControl {
    
    public static void saveRecords(List<Team> es) {
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
    
    public static List<Team> getRecords() {
        SessionFactory factory = null;
        try {
            factory = DbControl.setUp();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = factory.openSession();
        session.beginTransaction();
        List<Team> tl = session.createQuery("FROM Team T ORDER BY T.id").list();
        try {
            DbControl.tearDown(factory);
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tl;
    }
    
    public static List<Team> InitialLoad() {
        Team[] ta = new Team[3];
        ta[0] = new Team(1,"SLT","Senior Leadership");
        ta[1] = new Team(2,"Sales","Sales and Marketing");
        ta[2] = new Team(3,"Hybrid","Service Delivery");
        
        List<Team> tl = Arrays.asList(ta);
        saveRecords(tl);
        return tl;
    }
    
    public static String formatHtml(List<Team> tl) {
        String html = "<html>\n" +
        "    <head>\n" +
        "        <title>Teams List</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <h1>Teams List</h1>\n" +
        "        <div>\n" +
        "        <a href=\".CreateTeam\">Create Team</a>\n" +
        "        <table>\n" +
        "        <th>ID</th><th>Name</th><th>Description</th>\n";
        
        ListIterator<Team> li = tl.listIterator();
        while (li.hasNext()) {
            Team t = li.next();
            html += "        <tr><td>" + t.getId() + "</td><td>" + 
                    t.getName() + "</td><td>" + 
                    t.getDesc() + "</td><td>" +
                    "<a href=\".UpdateTeam/?id=" + t.getId() + "\">Update Team</a></td><td>" + 
                    "<a href=\".DeleteTeam/?id=" + t.getId() + "\">Delete Team</a><td></tr>\n";
        }
        html += "        </table>\n" +
        "        </div>\n" +
        "    </body>\n" +
        "</html>";
        
        return html;
    }
    
}
