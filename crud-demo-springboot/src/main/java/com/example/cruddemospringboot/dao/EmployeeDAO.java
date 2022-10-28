package com.example.cruddemospringboot.dao;

import java.util.List;

import com.example.cruddemospringboot.model.Employee;

public interface EmployeeDAO {
    public List<Employee> findAll();

    public Employee findById(int id);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(int id);
}
