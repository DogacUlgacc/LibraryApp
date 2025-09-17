package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.memberDto.request.CreateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.response.CreatedMemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberListResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.UpdatedMemberResponseDto;
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
    public List<MemberListResponseDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberResponseDto getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    @PostMapping()
    public CreatedMemberResponseDto save(@RequestBody CreateMemberRequestDto requestDto) {
        return memberService.save(requestDto);
    }

    @PutMapping("/{id}")
    public UpdatedMemberResponseDto update(@RequestBody UpdateMemberRequestDto requestDto, @PathVariable int id) {
        return memberService.update(requestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        memberService.delete(id);
    }
}
