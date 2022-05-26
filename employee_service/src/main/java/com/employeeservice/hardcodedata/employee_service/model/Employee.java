package com.employeeservice.hardcodedata.employee_service.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class Employee {
    private int empID;
    @Size(min=2, max=10, message="Invalid characters for Name")
    private String name;
    @Email
    private String email;

    public Employee(int empID, String name, String email){
        this.empID = empID;
        this.name = name;
        this.email = email;
    }

    public int getEmployeeID(){
        return empID;
    }

    public void setEmployeeID(int empID){
        this.empID = empID;
    }

    public String getEmployeeName(){
        return name;
    }

    public void setEmployeeName(String name){
        this.name = name;
    }

    public String getEmployeeEmail(){
        return email;
    }

    public void setEmployeeEmail(String email){
        this.email = email;
    }
}
