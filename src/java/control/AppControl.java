/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.HashMap;
import view.Handler;
import view.ListEmployees;
import view.ListTeams;

/**
 *
 * @author Dale
 */
public class AppControl {
    public static HashMap<String,Handler> commands = new HashMap<>();
    
    public void AppControl(){
    }
    
    public static void fillMap() {
        commands.put("team", new ListTeams());
        commands.put("employee", new ListEmployees());
    }
    
    public static String runCommand(String cmd)  {
        Handler handle = commands.get(cmd);
        return handle.execute();
    }
}
