package com.example.employee_service_sql.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long empID;
    @Size(min=2, max=10, message="Invalid characters for Name")
    private String name;
    @Email
    private String email;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<Department> allDepartments;

    public Employee(){}

    public Employee(long empID, String name, String email, List<Department> allDepartments){
        this.empID = empID;
        this.name = name;
        this.email = email;
        this.allDepartments = allDepartments;
    }

    public long getEmployeeID(){
        return empID;
    }

    public void setEmployeeID(long empID){
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

    public List<Department> getDepartment(){
        return allDepartments;
    }

    public void setDepartment(List<Department> allDepartments){
        this.allDepartments = allDepartments;
    }
}
