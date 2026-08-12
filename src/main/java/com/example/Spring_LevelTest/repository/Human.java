package com.example.Spring_LevelTest.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Human {

    private Integer id;
    private String name;
    private boolean isDeleted;

    public Human(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.isDeleted = false;
    }
}
