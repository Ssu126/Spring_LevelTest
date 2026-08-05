package com.example.Spring_LevelTest.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Human {

    Integer id;
    String name;

    public void setId(Integer id) {
        if (id != null && !id.equals(this.id)) {
            this.id = id;
        }
    }
}
