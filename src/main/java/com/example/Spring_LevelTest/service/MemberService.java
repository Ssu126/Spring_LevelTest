package com.example.Spring_LevelTest.service;

import com.example.Spring_LevelTest.controller.dto.MemberCreateRequestDto;
import com.example.Spring_LevelTest.controller.dto.MemberResponseDto;
import com.example.Spring_LevelTest.repository.IRepository;
import com.example.Spring_LevelTest.repository.member.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final IRepository<Integer, Member> memberRepository;

    public MemberResponseDto create(MemberCreateRequestDto requestDto) {
        Member member = requestDto.toMember(null);
        Member createdMember = memberRepository.create(member);
        return MemberResponseDto.from(createdMember);
    }

    public List<MemberResponseDto> readAll() {
        return memberRepository.readAll().stream()
            .map(MemberResponseDto::from)
            .toList();
    }

    public MemberResponseDto read(Integer id) {
        Member member = memberRepository.read(id);
        return MemberResponseDto.from(member);
    }

    public MemberResponseDto update(Integer id, MemberCreateRequestDto requestDto) {
        Member member = requestDto.toMember(id);
        Member updatedMember = memberRepository.update(member);
        return MemberResponseDto.from(updatedMember);
    }

    public void delete(Integer id) {
        memberRepository.delete(id);
    }
}
