package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.memberDto.request.CreateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.response.CreatedMemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberListResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.UpdatedMemberResponseDto;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberListResponseDto> getAllMembers() {

        return memberRepository.findAll().stream().map(member -> new MemberListResponseDto(member.getName(), member.getSurname(), member.getEmail(), member.getPhone(), member.getAddress(), member.getStatus())).toList();
    }

    public CreatedMemberResponseDto save(CreateMemberRequestDto requestDto) {
        Member member = new Member();
        member.setName(requestDto.getName());
        member.setSurname(requestDto.getSurname());
        member.setEmail(requestDto.getEmail());
        member.setPhone(requestDto.getPhone());
        member.setAddress(requestDto.getAddress());
        member.setStatus(requestDto.getStatus());
        member.setMembershipDate(LocalDate.now());

        Member savedMember = memberRepository.save(member);
        return new CreatedMemberResponseDto(savedMember.getName(), savedMember.getSurname(), savedMember.getEmail(), savedMember.getPhone(), savedMember.getAddress(), savedMember.getStatus());
    }

    public MemberResponseDto getMemberById(int id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        return new MemberResponseDto(member.getName(), member.getSurname(), member.getEmail(), member.getPhone(), member.getAddress(), member.getStatus());
    }


    //TODO :: ResponseBody null değerler dönüyor. Check it

    public UpdatedMemberResponseDto update(UpdateMemberRequestDto requestDto, int id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setPhone(requestDto.getPhone());
        member.setAddress(requestDto.getAddress());

        System.out.println(member.getEmail());
        memberRepository.save(member);
        System.out.println(member.getEmail());


        return new UpdatedMemberResponseDto(
                member.getName(), member.getSurname(), member.getEmail(), member.getPhone(), member.getAddress(), member.getStatus());
    }


    public void delete(int id) {
        memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        memberRepository.deleteById(id);
    }
}
