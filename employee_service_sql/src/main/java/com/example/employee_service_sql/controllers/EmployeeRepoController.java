package com.example.employee_service_sql.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.example.employee_service_sql.exception.EmployeeNotFound;
import com.example.employee_service_sql.model.Department;
import com.example.employee_service_sql.model.DepartmentRepository;
import com.example.employee_service_sql.model.Employee;
import com.example.employee_service_sql.model.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmployeeRepoController {
    
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/jpa/employees")
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    @GetMapping("/jpa/employees/id")
    public EntityModel<Employee> getEmployeeByID(@RequestParam Long empID){
        Employee e = employeeRepository.findById(empID).get();
        if(e == null){
            throw new EmployeeNotFound("Employee Not Found");
        }
        EntityModel<Employee> model = EntityModel.of(e);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-employees");
        model.add(link);
        return model;
    }

    @PostMapping("/jpa/employees/new")
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp){
        Employee e = employeeRepository.save(emp);
        return new ResponseEntity<Object>(e, HttpStatus.CREATED);
    }

    @PostMapping("/jpa/department/new/{empID}")
    public ResponseEntity<Object> saveDepartment(@Valid @RequestBody Department department, @PathVariable long empID){
        Employee e = employeeRepository.findById(empID).get();
        if(e == null){
            throw new EmployeeNotFound("Employee Not Found");
        }

        department.setEmployee(e);
        departmentRepository.save(department);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{empID}").buildAndExpand(e.getEmployeeID()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("jpa/employees/delete")
    public void deleteEmployee(@RequestParam long empID){
        Employee e = employeeRepository.findById(empID).get();
        if(e == null){
            throw new EmployeeNotFound("Employee Not Found");
        }
        employeeRepository.delete(e);
    }
    
}
