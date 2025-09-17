package com.grup_7.LibraryApp.mapper;

import com.grup_7.LibraryApp.dto.memberDto.request.CreateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.request.UpdateMemberRequestDto;
import com.grup_7.LibraryApp.dto.memberDto.response.CreatedMemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberListResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.MemberResponseDto;
import com.grup_7.LibraryApp.dto.memberDto.response.UpdatedMemberResponseDto;
import com.grup_7.LibraryApp.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    // Request DTO -> Entity
    Member createMemberRequestDtoToMember(CreateMemberRequestDto dto);
    Member updateMemberRequestDtoToMember(UpdateMemberRequestDto dto);

    // Entity -> Response DTO
    CreatedMemberResponseDto memberToCreatedMemberResponseDto(Member member);
    UpdatedMemberResponseDto memberToUpdatedMemberResponseDto(Member member);
    MemberResponseDto memberToMemberResponseDto(Member member);
    MemberListResponseDto memberToMemberListResponseDto(Member member);

    // List mapping
    List<MemberListResponseDto> membersToMemberListResponseDtos(List<Member> members);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

}
