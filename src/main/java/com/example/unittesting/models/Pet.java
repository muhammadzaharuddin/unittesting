package com.example.unittesting.models;

import lombok.*;

@Data
public class Pet {

    private Integer id;
    private String type;
    private String gender;
    private Integer age;

    public Pet(int id, String type, String gender, int age) {
        this.id = id;
        this.type = type;
        this.gender = gender;
        this.age = age;
    }


}