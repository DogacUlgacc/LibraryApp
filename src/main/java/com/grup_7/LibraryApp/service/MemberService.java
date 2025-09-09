package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
    return  memberRepository.findAll();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
