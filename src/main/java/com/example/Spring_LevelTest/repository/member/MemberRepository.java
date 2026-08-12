package com.example.Spring_LevelTest.repository.member;

import com.example.Spring_LevelTest.repository.IRepository;
import com.example.Spring_LevelTest.repository.JobType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository implements IRepository<Integer, Member> {

    private final Map<Integer, Member> members = new HashMap<>();
    private int CURRENT_ID = 2;

    public MemberRepository() {
        members.put(1, new Member(1, "su1", 11, JobType.DEVELOPER, "su1@email.com"));
        members.put(2, new Member(2, "su2", 22, JobType.DESIGNER, "su2@email.com"));
    }

    protected void idGenerator(boolean isIncrease) {
        if (isIncrease) {
            ++CURRENT_ID;
        } else {
            --CURRENT_ID;
        }
    }

    @Override
    public Member create(Member entity) {
        idGenerator(true);
        int id = this.CURRENT_ID;

        if (Objects.nonNull(members.get(id))) {
            idGenerator(false);
            throw new RuntimeException("기존에 해당하는 아이디를 가진 엔티티가 존재합니다. : " + (id + 1));
        }
        entity.setId(id);
        Member created = members.put(id, entity);

        return created;
    }

    @Override
    public List<Member> readAll() {
        return members.values().stream()
            .filter(member -> !member.isDeleted())
            .toList();
    }

    @Override
    public Member read(Integer id) {
        Member member = Optional.ofNullable(members.get(id))
            .filter(m -> !m.isDeleted())
            .orElseThrow(() -> new RuntimeException("읽으려는 아이디가 존재하지 않습니다. id : " + id));

        return member;
    }

    @Override
    public Member update(Member entity) {
        int id = entity.getId();
        Member existMember = members.get(id);
        if (Objects.isNull(existMember) || existMember.isDeleted()) {
            throw new RuntimeException("업데이트 하려는 아이디가 존재하지 않습니다.");
        }

        if (Objects.nonNull(entity.getName())) {
            existMember.setName(entity.getName());
        }
        if (Objects.nonNull(entity.getAge())) {
            existMember.setAge(entity.getAge());
        }
        if (Objects.nonNull(entity.getJob())) {
            existMember.setJob(entity.getJob());
        }
        if (Objects.nonNull(entity.getEmail())) {
            existMember.setEmail(entity.getEmail());
        }

        return existMember;
    }

    @Override
    public void delete(Integer id) {
        if (Objects.isNull(members.get(id))) {
            throw new RuntimeException("삭제하려는 아이디가 존재하지 않습니다. id : " + id);
        }
        members.remove(id);
        idGenerator(false);
    }
}