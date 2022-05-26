package com.employeeservice.hardcodedata.employee_service.model;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {
    static List<Employee> l = new ArrayList<Employee>();

    static{
        l.add(new Employee(123, "ABC", "abc@gmail.com"));
        l.add(new Employee(456, "DEF", "def@gmail.com"));
    }

    public List<Employee> getAllEmployees(){
        return l;
    }

    public Employee getEmployeeByID(int empID){
        return l.stream().filter(emp -> emp.getEmployeeID() == empID).findAny().orElse(null);
    }

    public Employee saveEmployee(Employee e){
        l.add(e);

        return e;
    }

    public Employee deleteEmployee(int e){
        Iterator<Employee> i = l.iterator();

        while(i.hasNext()){
            Employee emp = i.next();
            if(e == emp.getEmployeeID()){
                i.remove();
                return emp;
            }
        }
        return null;
    }
}
