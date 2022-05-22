package com.example.loosecoupling;

import com.example.loosecoupling.looseCoupling.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LoosecouplingApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LoosecouplingApplication.class, args);
		ApplicationContext context = SpringApplication.run(LoosecouplingApplication.class, args);
		Job job = context.getBean(Doctor.class);
		job.display();
	}

}
