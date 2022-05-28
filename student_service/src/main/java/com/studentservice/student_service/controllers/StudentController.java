package com.studentservice.student_service.controllers;

import java.util.List;
import java.util.Optional;

import com.studentservice.student_service.model.Book;
import com.studentservice.student_service.model.Student;
import com.studentservice.student_service.model.StudentRepository;
import com.studentservice.student_service.service.StudentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentDao dao;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/student/save")
    public void saveStudent(@RequestBody Student student){
        dao.insertStudent(student);
    }
    @PostMapping("/student/persist")
    public void persistStudent(@RequestBody Student student){
        studentRepository.save(student);
    }
    @GetMapping("/student")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/student/id")
    public Student getStudentByID(@RequestParam long id){
        Optional<Student> s = studentRepository.findById(id);
        return s.get();
    }

    @GetMapping("/book/id")
    public List<Book> getBooksByID(@RequestParam long id){
        Optional<Student> s = studentRepository.findById(id);
        return s.get().getAllBooks();
    }
}
