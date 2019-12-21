package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import lombok.Data;

/**
 * Person
 */
@Data
@KeySpace("person")
public class Person {

    @Id
    private Integer id;
    
    private String name;

}