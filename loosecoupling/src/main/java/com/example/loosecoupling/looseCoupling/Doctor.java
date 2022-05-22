package com.example.loosecoupling.looseCoupling;

import org.springframework.stereotype.Component;

@Component
public class Doctor implements Job {
    @Override
    public void display(){
        System.out.println("Doctor");
    }
}
