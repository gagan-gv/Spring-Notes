package com.example.loosecoupling.looseCoupling;

import org.springframework.beans.factory.annotation.Autowired;

public class AutoWired {
    @Autowired 
    Job job;

    public void showData(){
        job.display();
    }
}
