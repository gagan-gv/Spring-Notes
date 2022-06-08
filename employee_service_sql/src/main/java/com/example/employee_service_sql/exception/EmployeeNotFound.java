package com.example.employee_service_sql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound(String msg){
        super(msg);
    }
}
