package com.example.Spring_LevelTest.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Member extends com.example.Spring_LevelTest.model.Human {

    Integer age;
    com.example.Spring_LevelTest.model.JobType job;
    String email;

    public Member(String name, Integer age, com.example.Spring_LevelTest.model.JobType job,
        String email) {
        super(null, name);
        this.age = age;
        this.job = job;
        this.email = email;
    }
}
