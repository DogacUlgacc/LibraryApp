package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/all")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("/add")
    public Member addMember(@RequestBody Member member) {
        return memberService.save(member);
    }
}