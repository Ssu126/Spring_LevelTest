package com.example.Spring_LevelTest.controller.dto;

import com.example.Spring_LevelTest.repository.JobType;
import com.example.Spring_LevelTest.repository.member.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberCreateRequestDto {

    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    private JobType job;
    private String email;

    public Member toMember() {
        return new Member(this.name, this.age, this.job, this.email);
    }
}
