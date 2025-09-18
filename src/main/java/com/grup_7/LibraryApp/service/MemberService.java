package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import com.grup_7.LibraryApp.dto.memberDto.request.CreateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.response.CreatedMemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberListResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.UpdatedMemberResponseDto;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.mapper.MemberMapper;
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
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberBusinessRules memberBusinessRules, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberBusinessRules = memberBusinessRules;
        this.memberMapper = memberMapper;
    }

    public List<MemberListResponseDto> getAllMembers() {
        return memberMapper.toMemberListResponseDtoList(memberRepository.findAll());
    }

    public CreatedMemberResponseDto save(@Valid CreateMemberRequestDto requestDto) {
        memberBusinessRules.emailMustNotExistWithSameName(requestDto.getEmail());
        memberBusinessRules.phoneNumberMustNotExistWithSamePhoneNumber(requestDto.getPhone());
        memberBusinessRules.validatePhoneNumber(requestDto.getPhone());

        Member member = memberMapper.toMember(requestDto);
        Member savedMember = memberRepository.save(member);

         return memberMapper.toCreatedMemberResponseDto(savedMember);}

    public MemberResponseDto getMemberById(int id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new BusinessException("Member not found"));
        return memberMapper.toMemberResponseDto(member);
    }

    public UpdatedMemberResponseDto update(UpdateMemberRequestDto requestDto, int id) {
        memberBusinessRules.validatePhoneNumber(requestDto.getPhone());

        Member member = memberRepository.findById(id).orElseThrow(() -> new BusinessException("Member not found"));
        memberMapper.updateMemberFromDto(requestDto, member);

        Member updatedMember = memberRepository.save(member);

        return memberMapper.toUpdatedMemberResponseDto(updatedMember);
    }
    public void delete(int id) {
        memberRepository.findById(id).orElseThrow(() -> new BusinessException("Böyle bir üye bulunamadı."));
        memberRepository.deleteById(id);
    }
}
