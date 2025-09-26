package com.grup_7.LibraryApp.dto.finesDto.response;

import java.time.LocalDate;

public class FinesListResponse {

    private int id;
    private double amount;
    private LocalDate dueDate;
    private boolean isPaid;
    private String fineType;
    private int reservationId;
    private String memberName;
    private String bookTitle;


    public FinesListResponse() {
    }


    public FinesListResponse(int id, double amount, LocalDate dueDate, boolean isPaid, String fineType, int reservationId, String memberName, String bookTitle) {
        this.id = id;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
        this.fineType = fineType;
        this.reservationId = reservationId;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getIsPaid() { return isPaid; }
    public void setIsPaid(boolean isPaid) { this.isPaid = isPaid; }

    public String getFineType() {
        return fineType;
    }

    public void setFineType(String fineType) {
        this.fineType = fineType;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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
}
