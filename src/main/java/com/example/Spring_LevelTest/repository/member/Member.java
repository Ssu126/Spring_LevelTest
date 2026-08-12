package com.example.Spring_LevelTest.repository.member;

import com.example.Spring_LevelTest.repository.Human;
import com.example.Spring_LevelTest.repository.JobType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member extends Human {

    //도메인에서 예외처리 -> 완전해야 한다.
    private Integer age;
    private JobType job = JobType.DEVELOPER;
    private String email;

    public Member(Integer id, String name, Integer age, JobType job,
        String email) {
        super(id, name);
        this.age = age;
        this.job = job;
        this.email = email;
    }

    public Member(String name, Integer age, JobType job, String email) {
        super(null, name);
        this.age = age;
        this.job = job;
        this.email = email;
    }

    public void update(String name, Integer age, JobType job, String email) {
        super.setName(name);
        this.age = age;
        this.job = job;
        this.email = email;
    }

    public String toString() {
        return String.format("Member(id=%d, name=%s, age=%d, job=%s, email=%s)",
            super.getId(), super.getName(), this.age, this.job, this.email);
    }
}
