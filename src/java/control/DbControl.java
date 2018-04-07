/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Dale
 */
public class DbControl {
    
    protected static SessionFactory setUp() throws Exception {
        return new Configuration().configure().buildSessionFactory();
    }
    
    protected static void tearDown(SessionFactory factory) throws Exception {
        factory.close();
    }
    
}
