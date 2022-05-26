package com.employeeservice.hardcodedata.employee_service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@RestController
@ControllerAdvice
public class employeeGlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e, WebRequest req){
        EmployeeExceptionResponse exResponse = new EmployeeExceptionResponse(e.getMessage(), req.getDescription(false), new Date());

        return new ResponseEntity<Object>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(Exception e, WebRequest req){
        EmployeeExceptionResponse exResponse = new EmployeeExceptionResponse(e.getMessage(), req.getDescription(false), new Date());

        return new ResponseEntity<Object>(exResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req){
        EmployeeExceptionResponse exResponse = new EmployeeExceptionResponse("Invalid Input", ex.getBindingResult().toString(), new Date());
        
        return new ResponseEntity<Object>(exResponse, HttpStatus.BAD_REQUEST);
    }
}
