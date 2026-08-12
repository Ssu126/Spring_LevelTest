package com.example.Spring_LevelTest.controller.dto;

import com.example.Spring_LevelTest.repository.JobType;
import com.example.Spring_LevelTest.repository.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MemberResponseDto {

    Integer id;
    String name;
    Integer age;
    JobType job;
    String email;

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
