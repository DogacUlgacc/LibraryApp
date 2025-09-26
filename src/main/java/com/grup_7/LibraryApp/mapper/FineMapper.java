package com.grup_7.LibraryApp.mapper;

import com.grup_7.LibraryApp.dto.finesDto.request.CreateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.request.UpdateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.response.CreatedFinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesListResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.UpdateFinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesResponse;
import com.grup_7.LibraryApp.entity.Fine;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;
@Mapper(componentModel = "spring")
public interface FineMapper {

    @Mapping(target = "paid", constant = "false")  // isPaid değil paid
    @Mapping(target = "reservation.id", source = "reservationId")
    Fine toFinesEntity(CreateFinesRequest request);


    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    CreatedFinesResponse toCreatedFinesResponse(Fine fine);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    FinesResponse toFinesResponse(Fine fine);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    FinesListResponse toFinesListResponse(Fine fine);

    List<FinesListResponse> toFinesListResponseList(List<Fine> fineList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    void updateFinesFromRequest(UpdateFinesRequest request, @MappingTarget Fine fine);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    UpdateFinesResponse toUpdateFinesResponse(Fine fine);


}
