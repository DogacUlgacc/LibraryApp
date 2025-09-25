package com.grup_7.LibraryApp.repository;

import com.grup_7.LibraryApp.dto.memberDto.response.MemberResponseDto;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.enums.member.MembershipLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findTop1ByEmailIgnoreCase(String email);

    Optional<Member> findTop1ByPhoneIgnoreCase(String phone);

    List<Member> findByMembershipLevel(MembershipLevel membershipLevel);

    List<Member> findByEmail(String email);

    List<Member> findByMembershipLevelAndEmail(MembershipLevel membershipLevel, String email);}
