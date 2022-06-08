package com.example.employee_service_sql.exception;

import java.util.Date;

public class EmployeeExceptionResponse {
    private String errorMessage, description;
    private Date dateAndTime;

    public EmployeeExceptionResponse(String errorMessage, String description, Date dateAndTime){
        this.errorMessage = errorMessage;
        this.description = description;
        this.dateAndTime = dateAndTime;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Date getDate(){
        return dateAndTime;
    }

    public void setDate(Date dateAndTime){
        this.dateAndTime = dateAndTime;
    }
}