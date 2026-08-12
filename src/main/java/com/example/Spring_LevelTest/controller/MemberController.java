package com.example.Spring_LevelTest.controller;

import com.example.Spring_LevelTest.controller.dto.MemberCreateRequestDto;
import com.example.Spring_LevelTest.controller.dto.MemberResponseDto;
import com.example.Spring_LevelTest.repository.JobType;
import com.example.Spring_LevelTest.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/users")
    public ModelAndView getAllUserPage(ModelAndView modelAndView) {
        List<MemberResponseDto> responseList = memberService.readAll();
        log.info("PAGE 전체 조회 : {}", responseList);
        modelAndView.addObject("users", responseList);
        modelAndView.setViewName("/list");
        return modelAndView;
    }

    @GetMapping("/users/{id}")
    public String getUserPage(@PathVariable(name = "id", required = true) Integer id, Model model) {
        MemberResponseDto responseDto = memberService.read(id);
        log.info("PAGE 단일 조회: {}", responseDto);
        model.addAttribute("id", responseDto.getId());
        model.addAttribute("name", responseDto.getName());
        model.addAttribute("age", responseDto.getAge());
        model.addAttribute("job", responseDto.getJob());
        model.addAttribute("email", responseDto);
        return "detail";
    }

    @PostMapping("/api/users")
    @ResponseBody
    public MemberResponseDto createAPI(@RequestBody @Valid MemberCreateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.create(requestDto);
        log.info("API 단일 생성 : {}", responseDto);
        return responseDto;
    }

    @GetMapping("/api/users")
    @ResponseBody
    public List<MemberResponseDto> readAllAPI() {
        List<MemberResponseDto> responseDtoList = memberService.readAll();
        log.info("API 전체 조회: {}", responseDtoList);
        return responseDtoList;
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto readAPI(@PathVariable(name = "id", required = true) Integer id) {
        MemberResponseDto responseDto = memberService.read(id);
        log.info("API 단일 조회: {}", responseDto);
        return responseDto;
    }

    @PatchMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto updatePartialAPI(
        @PathVariable(name = "id", required = true) Integer id,
        @RequestParam String name,
        @RequestParam Integer age,
        @RequestParam JobType job,
        @RequestParam String email
    ) {
        MemberResponseDto origin = memberService.read(id);
        MemberCreateRequestDto requestDto = new MemberCreateRequestDto();
        if (Objects.nonNull(name)) {
            requestDto.setName(name);
        }
        if (Objects.nonNull(age)) {
            requestDto.setAge(age);
        }
        if (Objects.nonNull(job)) {
            requestDto.setJob(job);
        }
        if (Objects.nonNull(email)) {
            requestDto.setEmail(email);
        }

        MemberResponseDto responseDto = memberService.update(id, requestDto);
        log.info("API 단일 부분 수정: {}", responseDto);
        return responseDto;
    }

    @PutMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto updateAllAPI(
        @PathVariable(name = "id", required = true) Integer id,
        @RequestBody @Valid MemberCreateRequestDto requestDto) {
        if (Objects.isNull(requestDto.getJob()) || Objects.isNull(requestDto.getEmail())) {
            throw new IllegalArgumentException("모든 필드가 필수 입력값입니다.");
        }

        MemberResponseDto responseDto = memberService.update(id, requestDto);
        log.info("API 회원 전체 수정: {}", responseDto);
        return responseDto;
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto deleteAPI(@PathVariable(name = "id", required = true) Integer id) {
        MemberResponseDto responseDto = memberService.read(id);
        memberService.delete(id);
        log.info("API 단일 삭제: {}", responseDto);
        return responseDto;
    }
}
