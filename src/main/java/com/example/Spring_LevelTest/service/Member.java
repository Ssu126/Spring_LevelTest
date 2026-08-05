package com.example.Spring_LevelTest.service;

import com.example.Spring_LevelTest.controller.dto.JobType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member extends Human {

    Integer age;
    JobType job;
    String email;

    public Member(Integer id, String name, Integer age, JobType job, String email) {
        super(id, name);
        this.age = age;
        this.job = job;
        this.email = email;
    }

    public String toString() {
        return String.format("Member(name=%s, age=%d, job=%s, email=%s)",
            super.getName(), age, job, email);
    }
}
