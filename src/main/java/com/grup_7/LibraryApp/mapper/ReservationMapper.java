package com.grup_7.LibraryApp.mapper;

import com.grup_7.LibraryApp.dto.reservationDto.request.CreateReservationRequest;
import com.grup_7.LibraryApp.dto.reservationDto.request.UpdateReservationRequest;
import com.grup_7.LibraryApp.dto.reservationDto.response.CreatedReservationResponse;
import com.grup_7.LibraryApp.dto.reservationDto.response.ReservationListResponse;
import com.grup_7.LibraryApp.dto.reservationDto.response.ReservationResponse;
import com.grup_7.LibraryApp.dto.reservationDto.response.UpdatedReservationResponse;
import com.grup_7.LibraryApp.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "expireAt", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "book", ignore = true)
    Reservation toReservationEntity(CreateReservationRequest request);

    ReservationResponse toReservationResponse(Reservation reservation);

    CreatedReservationResponse toCreatedReservationResponse(Reservation reservation);

    UpdatedReservationResponse toUpdatedReservationResponse(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "expireAt", ignore = true)
    void updateReservationFromRequest(UpdateReservationRequest request, @MappingTarget Reservation reservation);

    ReservationListResponse toReservationListResponse(Reservation reservation);

    List<ReservationListResponse> toReservationListResponseList(List<Reservation> reservations);





}
