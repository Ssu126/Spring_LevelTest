package com.example.Spring_LevelTest.service;

import com.example.Spring_LevelTest.controller.dto.JobType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository implements IRepository {

    private int idCount = 2;
    private final Map<Integer, Member> members = new HashMap<>();

    public MemberRepository() {
        members.put(1, new Member(1, "su1", 11, JobType.DEVELOPER, "su1@email.com"));
        members.put(2, new Member(2, "su2", 22, JobType.DESIGNER, "su2@email.com"));
    }

    private Integer idGenerator(boolean isIncrement) {
        if (isIncrement) {
            return ++idCount;
        } else {
            return --idCount;
        }
    }

    @Override
    public Member create(Member entity) {
        Integer newId = idGenerator(true);
        entity.setId(newId);
        members.put(newId, entity);
        return members.get(newId);
    }

    @Override
    public List<Member> readAll() {
        return this.members.values().stream().toList();
    }

    @Override
    public Member read(Integer id) {
        Member memberNullable = this.members.get(id);
        return Optional.ofNullable(memberNullable)
            .orElseThrow(() -> new NoSuchElementException("Member id " + id + " not found"));
    }

    @Override
    public Member update(Member entity) {
        Member memberNullable = this.members.get(entity.getId());
        Optional.ofNullable(memberNullable)
            .orElseThrow(
                () -> new NoSuchElementException("Member id " + entity.getId() + " not found"));
        members.put(entity.getId(), entity);
        return members.get(entity.getId());
    }

    @Override
    public void delete(Integer id) {
        if (!this.members.containsKey(id)) {
            throw new NoSuchElementException("Member id" + id + "not found");
        }
        idGenerator(false);
        this.members.remove(id);
    }
}
