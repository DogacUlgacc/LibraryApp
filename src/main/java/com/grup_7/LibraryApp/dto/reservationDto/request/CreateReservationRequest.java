package com.grup_7.LibraryApp.dto.reservationDto.request;

import java.time.LocalDate;

public class CreateReservationRequest {

    private int memberId;
    private int bookId;
    private LocalDate reservationDate;

    public CreateReservationRequest() {}

    public CreateReservationRequest(int memberId, int bookId, LocalDate reservationDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}