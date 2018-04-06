/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Team;
import org.hibernate.Hibernate;

/**
 *
 * @author Dale
 */
public class TeamControl {
    
    private static SessionFactory factory;
    
    protected static void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
    }
    
    protected static void tearDown() throws Exception {
        factory.close();
    }
    
    public static void saveRecords(List<Team> es) {
        try {
            setUp();
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
            tearDown();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Team> getRecords() {
        try {
            setUp();
        } catch (Exception ex) {
            Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = factory.openSession();
        session.beginTransaction();
        List<Team> tl = session.createQuery("FROM Team T ORDER BY T.name").list();
        try {
            tearDown();
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
    
}
