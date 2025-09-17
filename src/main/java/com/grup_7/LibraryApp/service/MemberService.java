package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import com.grup_7.LibraryApp.dto.memberDto.request.CreateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.response.CreatedMemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberListResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.UpdatedMemberResponseDto;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.repository.MemberRepository;
import com.grup_7.LibraryApp.rules.MemberBusinessRules;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberBusinessRules memberBusinessRules;
    public MemberService(MemberRepository memberRepository, MemberBusinessRules memberBusinessRules) {
        this.memberRepository = memberRepository;
        this.memberBusinessRules = memberBusinessRules;
    }

    public List<MemberListResponseDto> getAllMembers() {
        return memberRepository.findAll().stream().map(member -> new MemberListResponseDto(member.getName(), member.getSurname(), member.getEmail(), member.getPhone(), member.getAddress(), member.getMembershipLevel())).toList();
    }

    public CreatedMemberResponseDto save(@Valid CreateMemberRequestDto requestDto) {
        memberBusinessRules.emailMustNotExistWithSameName(requestDto.getEmail());
        memberBusinessRules.phoneNumberMustNotExistWithSamePhoneNumber(requestDto.getPhone());
        memberBusinessRules.validatePhoneNumber(requestDto.getPhone());

        Member member = new Member();
        member.setName(requestDto.getName());
        member.setSurname(requestDto.getSurname());
        member.setEmail(requestDto.getEmail());
        member.setPhone(requestDto.getPhone());
        member.setAddress(requestDto.getAddress());
        member.setMembershipLevel(requestDto.getMembershipLevel());
        member.setMembershipDate(LocalDate.now());

        Member savedMember = memberRepository.save(member);
        return new CreatedMemberResponseDto(savedMember.getName(), savedMember.getSurname(), savedMember.getEmail(), savedMember.getPhone(), savedMember.getAddress(), savedMember.getMembershipLevel());
    }

    public MemberResponseDto getMemberById(int id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        return new MemberResponseDto(member.getName(), member.getSurname(), member.getEmail(), member.getPhone(), member.getAddress(), member.getMembershipLevel());
    }

    public UpdatedMemberResponseDto update(UpdateMemberRequestDto requestDto, int id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setPhone(requestDto.getPhone());
        member.setAddress(requestDto.getAddress());

        System.out.println(member.toString());
        System.out.println("****************");
        memberRepository.save(member);
        System.out.println(member.toString());


        return new UpdatedMemberResponseDto(
                member.getName(),
                member.getSurname(),
                member.getEmail(),
                member.getPhone(),
                member.getAddress(),
                member.getMembershipLevel());
    }

    public void delete(int id) {
        memberRepository.findById(id).orElseThrow(() -> new BusinessException("Böyle bir üye bulunamadı."));
        memberRepository.deleteById(id);
    }
}
