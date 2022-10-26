package com.example.springrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.models.Student;

@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/get-all")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Gagan", "Chordia"));
        students.add(new Student("ABC", "DEF"));

        return students;
    }
}
