package com.grup_7.LibraryApp.mapper;

import com.grup_7.LibraryApp.dto.memberDto.request.CreateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.response.CreatedMemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberListResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.UpdatedMemberResponseDto;
import com.grup_7.LibraryApp.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface MemberMapper {


    // Tek entity -> DTO
    MemberListResponseDto toMemberListResponseDto(Member member);

    // Listeyi DTO listesine çevir
    List<MemberListResponseDto> toMemberListResponseDtoList(List<Member> members);

    CreatedMemberResponseDto toCreatedMemberResponseDto(Member member);

    @Mapping(target = "memberId", ignore = true)
    @Mapping(target = "membershipDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "loans", ignore = true)
    Member toMember(CreateMemberRequestDto createMemberRequestDto);

    MemberResponseDto toMemberResponseDto(Member member);

    @Mapping(target = "memberId", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "membershipLevel", ignore = true)
    @Mapping(target = "membershipDate", ignore = true)
    @Mapping(target = "loans", ignore = true)
    void updateMemberFromDto(UpdateMemberRequestDto dto, @MappingTarget Member member);

    UpdatedMemberResponseDto toUpdatedMemberResponseDto(Member member);
}

