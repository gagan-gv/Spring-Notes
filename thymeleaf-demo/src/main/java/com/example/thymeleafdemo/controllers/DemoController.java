package com.example.thymeleafdemo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.thymeleafdemo.models.*;

@Controller
public class DemoController {
    
    List<Employee> employees;

    @PostConstruct
    private void loadData() {
        employees = new ArrayList<>();

        employees.add(new Employee("Alpha", 1));
        employees.add(new Employee("Beta", 2));
        
    }

    @GetMapping("/hello")
    public String sayHello(Model model) {

        model.addAttribute("theDate", new java.util.Date());
        model.addAttribute("employees", employees);

        return "helloworld";
    }
}
