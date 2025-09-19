package com.grup_7.LibraryApp.mapper;

import com.grup_7.LibraryApp.dto.finesDto.request.CreateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.request.UpdateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.response.CreatedFinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesListResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.UpdateFinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesResponse;
import com.grup_7.LibraryApp.entity.Fines;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring")
public interface FinesMapper {



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isPaid", constant = "false")
    @Mapping(target = "reservation", source = "reservation")
    Fines toFinesEntity(CreateFinesRequest request);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    CreatedFinesResponse toCreatedFinesResponse(Fines fines);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    FinesResponse toFinesResponse(Fines fines);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    FinesListResponse toFinesListResponse(Fines fines);

    List<FinesListResponse> toFinesListResponseList(List<Fines> finesList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    void updateFinesFromRequest(UpdateFinesRequest request, @MappingTarget Fines fines);

    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "memberName", source = "reservation.member.name")
    @Mapping(target = "bookTitle", source = "reservation.book.title")
    UpdateFinesResponse toUpdateFinesResponse(Fines fines);


}
