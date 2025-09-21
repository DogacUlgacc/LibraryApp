package com.grup_7.LibraryApp.dto.reservationDto.response;

import java.time.LocalDate;

public class CreatedReservationResponse {

    private int id;
    private int memberId;
    private String memberName;
    private int bookId;
    private String bookTitle;
    private LocalDate reservationDate;
    private LocalDate expireAt;
    private String status;

    public CreatedReservationResponse() {}

    public CreatedReservationResponse(int id, int memberId, String memberName, int bookId, String bookTitle, LocalDate reservationDate, LocalDate expireAt, String status) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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
