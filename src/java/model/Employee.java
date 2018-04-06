/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dale
 */
public class Employee implements Serializable, Comparable<Employee> {
    
    // member variables
    private String firstName;
    private String lastName;
    private String title;
    private Integer empNumber;
    private Integer team_id;
    private Date hireDate;
    
    // custom comparison for employees, by hiredate, last name, first name
    @Override
    public int compareTo(Employee e) {
        int empComp = hireDate.compareTo(e.hireDate);
        empComp = (empComp != 0 ? empComp : lastName.compareTo(e.lastName));
        return (empComp != 0 ? empComp : firstName.compareTo(e.firstName));
    }

    // Default constructor
    public Employee() {
        
    }
    
    //Custom constructor
    public Employee(String firstName, String lastName, Integer empNumber, Integer team_id, Date hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empNumber = empNumber;
        this.team_id = team_id;
        this.hireDate = hireDate;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(Integer empNumber) {
        this.empNumber = empNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + this.empNumber;
        hash = 89 * hash + this.team_id;
        hash = 89 * hash + Objects.hashCode(this.hireDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.empNumber != other.empNumber) {
            return false;
        }
        if (this.team_id != other.team_id) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.hireDate, other.hireDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "firstName=" + firstName + ", lastName=" + lastName + ", empNumber=" + empNumber + ", team_id=" + team_id + ", hireDate=" + hireDate;
    }
}
