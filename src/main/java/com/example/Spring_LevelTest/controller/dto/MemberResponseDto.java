package com.example.Spring_LevelTest.controller.dto;

import com.example.Spring_LevelTest.service.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponseDto {

    private Integer id;
    private String name;
    private Integer age;
    private JobType job;
    private String email;

    public static MemberResponseDto from(Member entity) {
        return new MemberResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getAge(),
            entity.getJob(),
            entity.getEmail()
        );
    }
}
