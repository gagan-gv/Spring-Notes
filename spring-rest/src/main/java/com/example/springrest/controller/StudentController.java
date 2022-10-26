package com.example.springrest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.errors.StudentNotFoundException;
import com.example.springrest.models.Student;

@RestController
@RequestMapping("/student")
public class StudentController {

    List<Student> students = null;

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("Gagan", "Chordia"));
        students.add(new Student("ABC", "DEF"));
    }

    @GetMapping("/get-all")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/getStudent/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if(studentId > students.size() - 1 || studentId < 0) {
            throw new StudentNotFoundException("Student with id: " + studentId + " not found");
        }
        return students.get(studentId);
    }
}
