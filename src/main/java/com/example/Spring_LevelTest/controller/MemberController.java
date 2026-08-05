package com.example.Spring_LevelTest.controller;

import com.example.Spring_LevelTest.controller.dto.JobType;
import com.example.Spring_LevelTest.controller.dto.MemberCreateRequestDto;
import com.example.Spring_LevelTest.controller.dto.MemberResponseDto;
import com.example.Spring_LevelTest.service.MemberService;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MemberController {

    MemberService memberService;

    @GetMapping("/users")
    public ModelAndView getAllUsersPage(ModelAndView modelAndView) {
        List<MemberResponseDto> responseList = memberService.readAll();
        log.info("PAGE 전체 조회: {}", responseList);
        modelAndView.addObject("users", responseList);
        modelAndView.setViewName("/list");
        return modelAndView;
    }

    @GetMapping("/users/{id}")
    public String getUserPage(@PathVariable Integer id, Map<String, Object> model) {
        MemberResponseDto responseDto = memberService.read(id);
        log.info("PAGE 단일 조회: {}", responseDto);
        model.put("id", responseDto.getId());
        model.put("name", responseDto.getName());
        model.put("age", responseDto.getAge());
        model.put("job", responseDto.getJob());
        model.put("email", responseDto.getEmail());
        return "detail";
    }

    @PostMapping("/api/users")
    @ResponseBody
    public MemberResponseDto createAPI(@RequestBody MemberCreateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.create(requestDto);
        log.info("API 회원 생성 : {}", responseDto);
        return responseDto;
    }

    @GetMapping("/api/users")
    @ResponseBody
    public List<MemberResponseDto> readAllAPI() {
        List<MemberResponseDto> responseList = memberService.readAll();
        log.info("API 전체 조회: {}", responseList);
        return responseList;
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto readAPI(@PathVariable Integer id) {
        MemberResponseDto responseDto = memberService.read(id);
        log.info("API 단일 조회 : {}", responseDto);
        return responseDto;
    }

    @PatchMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto updatePartialAPI(
        @PathVariable Integer id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer age,
        @RequestParam(required = false) JobType job,
        @RequestParam(required = false) String email
    ) {
        MemberResponseDto origin = memberService.read(id);

        MemberCreateRequestDto requestDto = new MemberCreateRequestDto();
        requestDto.setName(name != null ? name : origin.getName());
        requestDto.setAge(age != null ? age : origin.getAge());
        requestDto.setJob(job != null ? job : origin.getJob());
        requestDto.setEmail(email != null ? email : origin.getEmail());

        MemberResponseDto responseDto = memberService.update(id, requestDto);
        log.info("API 단일 부분 수정 : {}", responseDto);
        return responseDto;
    }

    @PutMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto updateAllAPI(@PathVariable Integer id,
        @ModelAttribute MemberCreateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.update(id, requestDto);
        log.info("API 회원 전체 수정 : {}", responseDto);
        return responseDto;
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseBody
    public MemberResponseDto deleteAPI(@PathVariable Integer id) {
        MemberResponseDto responseDto = memberService.read(id);
        memberService.delete(id);
        log.info("API 단일 삭제 : {}", responseDto);
        return responseDto;
    }
}
