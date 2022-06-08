package com.example.employee_service_sql.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String deptName;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public Department(){}

    public Department(int id, String deptName){
        this.id = id;
        this.deptName = deptName;
    }

    public long getId(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getDeptName(){
        return deptName;
    }

    public void setDeptName(String deptName){
        this.deptName = deptName;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    
}
