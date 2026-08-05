package com.example.Spring_LevelTest.controller.dto;

import com.example.Spring_LevelTest.service.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberCreateRequestDto {

    private String name;
    private Integer age;
    private JobType job;
    private String email;

    public Member toMember(Integer id) {
        return new Member(id, name, age, job, email);
    }
}
