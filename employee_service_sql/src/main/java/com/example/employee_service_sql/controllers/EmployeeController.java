package com.example.employee_service_sql.controllers;

import java.util.*;

import javax.validation.Valid;

import com.example.employee_service_sql.exception.EmployeeNotFound;
import com.example.employee_service_sql.model.Employee;
import com.example.employee_service_sql.model.EmployeeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    
    @Autowired
    EmployeeDao service;

    @GetMapping("/employees")
    public List<Employee> getAll(){
        return service.getAllEmployees();
    }

    @GetMapping("/employees/id")
    public EntityModel<Employee> getEmployeeByID(@RequestParam int empID){
        Employee e = service.getEmployeeByID(empID);
        if(e == null){
            throw new EmployeeNotFound("Employee Not Found");
        }
        EntityModel<Employee> model = EntityModel.of(e);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-employees");
        model.add(link);
        return model;
    }

    @PostMapping("/employees/new")
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp){
        Employee e = service.saveEmployee(emp);
        return new ResponseEntity<Object>(e, HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/delete")
    public ResponseEntity<Object> deleteEmployee(@RequestParam int empID){
        Employee e = service.deleteEmployee(empID);
        if(e == null){
            throw new EmployeeNotFound("Employee Not Found");
        }
        return new ResponseEntity<Object>(e, HttpStatus.OK);
    }

}