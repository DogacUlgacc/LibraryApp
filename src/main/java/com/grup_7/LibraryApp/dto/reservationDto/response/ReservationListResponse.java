package com.grup_7.LibraryApp.dto.reservationDto.response;

import java.time.LocalDate;

public class ReservationListResponse {
    private int id;
    private String memberName;
    private String bookTitle;
    private LocalDate reservationDate;
    private LocalDate expireAt;
    private String status;

    public ReservationListResponse() {}

    public ReservationListResponse(int id, String memberName, String bookTitle, LocalDate reservationDate, LocalDate expireAt, String status) {
        this.id = id;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.reservationDate = reservationDate;
        this.expireAt = expireAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
