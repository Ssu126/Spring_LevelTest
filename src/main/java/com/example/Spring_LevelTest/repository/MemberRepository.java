package com.example.Spring_LevelTest.repository;

import com.example.Spring_LevelTest.model.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MemberRepository implements com.example.Spring_LevelTest.repository.IRepository {

    private final Map<Integer, Member> data = new HashMap<>();
    private int idCount = 0;

    private Integer idGenerator() {
        return ++idCount;
    }

    @Override
    public Member create(Member entity) {
        Integer newId = idGenerator();

        Member member = new Member(
            entity.getName(),
            entity.getAge(),
            entity.getJob(),
            entity.getEmail()
        );

        member.setId(newId);
        data.put(newId, member);

        return member;
    }

    @Override
    public List<Member> readAll() {
        return this.data.values().stream().toList();
    }

    @Override
    public Member read(Integer id) {
        Member memberNullable = this.data.get(id);

        return Optional.ofNullable(memberNullable)
            .orElseThrow(() -> new NoSuchElementException("Member id " + id + "not found"));
    }

    @Override
    public Member update(Member entity) {
        Member memberNullable = this.data.get(entity.getId());
        Member member = Optional.ofNullable(memberNullable)
            .orElseThrow(
                () -> new NoSuchElementException("Member id " + entity.getId() + "not fount"));

        data.put(entity.getId(), entity);

        return data.get(entity.getId());
    }

    @Override
    public void delete(Integer id) {
        if (!this.data.containsKey(id)) {
            throw new NoSuchElementException("Member id" + id + "not found");
        }
        this.data.remove(id);
    }
}
