/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.TeamControl;
import java.util.List;
import model.Team;

/**
 *
 * @author Dale
 */
public class ListTeams implements Handler {
    
    @Override
    public String execute() {
        List<Team> tl = TeamControl.getRecords();
        if (tl.isEmpty()) { tl = TeamControl.InitialLoad(); }
        return tl.toString();
    }
}
