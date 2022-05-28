package com.studentservice.student_service.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.studentservice.student_service.model.Student;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentDao {
    @PersistenceContext
    EntityManager em;

    public void insertStudent(Student student){
        em.persist(student);
    }
}
