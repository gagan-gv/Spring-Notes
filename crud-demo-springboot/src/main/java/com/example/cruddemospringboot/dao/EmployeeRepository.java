package com.example.cruddemospringboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cruddemospringboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
