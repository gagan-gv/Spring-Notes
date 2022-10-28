package com.example.cruddemospringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cruddemospringboot.dao.EmployeeDAO;
import com.example.cruddemospringboot.dao.EmployeeRepository;
import com.example.cruddemospringboot.model.Employee;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    /* private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImplementation(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    } */

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        Optional<Employee> res = employeeRepository.findById(id);

        Employee employee = null;

        if(res.isPresent()) {
            employee = res.get();
        }else {
            throw new RuntimeException("Employee Not Found");
        }

        return employee;
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
    
}
