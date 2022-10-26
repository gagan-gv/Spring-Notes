package com.luv2code.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        try {
            // create object mapper
            ObjectMapper objectMapper = new ObjectMapper();

            // read json and convert to java POJO
            Student student = objectMapper.readValue(new File("data/sample-full.json"), Student.class);

            // print first and last name
            System.out.println(student.getFirstName());
            System.out.println(student.getLastName());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
