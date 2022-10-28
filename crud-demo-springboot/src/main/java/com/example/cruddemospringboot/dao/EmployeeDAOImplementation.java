package com.example.cruddemospringboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/* import org.hibernate.query.Query;
import org.hibernate.Session; */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cruddemospringboot.model.Employee;

// Used for Hibernate & Standard JPA
@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /* Spring Boot with Hibernate
    @Override
    public List<Employee> findAll() {

        // get hibernate session
        Session session = entityManager.unwrap(Session.class);

        // create a query
        Query<Employee> query = session.createQuery("from Employee", Employee.class);

        // execute a query & get result
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        // get hibernate session
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void saveEmployee(Employee employee) {
        // get hibernate session
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
        
    }

    @Override
    public void deleteEmployee(int id) {
        // get hibernate session
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
        
    } */

    // Standard JPA
    @Override
    public void deleteEmployee(int id) {
        Query query = entityManager.createQuery("delete from Employee where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        Query query = entityManager.createQuery("from Employee");
        // execute a query
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Employee e = entityManager.merge(employee);
        employee.setId(e.getId());
    }
    
}
