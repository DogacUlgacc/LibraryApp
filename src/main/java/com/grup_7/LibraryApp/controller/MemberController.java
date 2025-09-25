package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.memberDto.request.CreateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMembershipLevelRequest;
import com.grup_7.LibraryApp.dto.memberDto.response.*;
import com.grup_7.LibraryApp.enums.member.MembershipLevel;
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

    //TODO:: Aslında  getMembers() methodunda çalışıyor bu ama swaggerda rahat görmek için kalsın sonradan sileriz
    @GetMapping("/all")
    public List<MemberListResponseDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberResponseDto getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    @GetMapping
    public List<MemberResponseDto> getMembers(
            @RequestParam(required = false) MembershipLevel membershipLevel,
            @RequestParam(required = false) String email) {
        return memberService.getMembers(membershipLevel, email);
    }

    @PostMapping()
    public CreatedMemberResponseDto save(@RequestBody CreateMemberRequestDto requestDto) {
        return memberService.save(requestDto);
    }

    @PutMapping("/{id}")
    public UpdatedMemberResponseDto update(@RequestBody UpdateMemberRequestDto requestDto, @PathVariable int id) {
        return memberService.update(requestDto, id);
    }

    @PatchMapping("/{id}/status-change")
    public UpdatedMembershipLevelResponse updateStatus(@RequestBody UpdateMembershipLevelRequest requestDto, @PathVariable int id) {
        return memberService.updateStatus(requestDto,id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        memberService.delete(id);
    }
}
