/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.EmployeeControl;
import java.util.List;
import model.Employee;

/**
 *
 * @author Dale
 */
public class ListEmployees implements Handler {
    
    @Override
    public String execute() {
        List<Employee> el = EmployeeControl.getRecords();
        if (el.isEmpty()) { el = EmployeeControl.InitialLoad(); }
        String html = EmployeeControl.formatHtmlList(el);
        return html;
    }
}
