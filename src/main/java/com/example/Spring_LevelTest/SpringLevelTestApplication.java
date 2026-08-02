package com.example.Spring_LevelTest;

import com.example.Spring_LevelTest.model.JobType;
import com.example.Spring_LevelTest.model.Member;
import com.example.Spring_LevelTest.repository.MemberRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLevelTestApplication {

    public static void main(String[] args) {
        MemberRepository repository = new MemberRepository();

        System.out.println("--회원 가입--");
        Member m1 = repository.create(new Member("su1", 11, JobType.DEVELOPER, "su1@email.com"));
        Member m2 = repository.create(new Member("su2", 22, JobType.DESIGNER, "su2@email.com"));

        System.out.println("\n--전체 조회--");
        System.out.println(repository.readAll());

        System.out.println("\n--단일 조회--");
        Member findMember = repository.read(2);
        System.out.println("2번 회원 : " + findMember);

        System.out.println("\n--정보 수정--");
        Member updateData = new Member("su3", 33, JobType.DEVELOPER, "su3@email.com");
        updateData.setId(2);
        Member updateMember = repository.update(updateData);
        System.out.println("수정 후 2번 결과: " + repository.read(2));

        System.out.println("\n--회원 삭제--");
        repository.delete(2);
        System.out.println("2번 삭제 후, 전체 회원: ");
        System.out.println(repository.readAll());

        //SpringApplication.run(SpringLevelTestApplication.class, args);
    }
}
