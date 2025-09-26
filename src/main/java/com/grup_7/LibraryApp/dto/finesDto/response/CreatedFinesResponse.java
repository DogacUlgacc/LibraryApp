package com.grup_7.LibraryApp.dto.finesDto.response;

import com.grup_7.LibraryApp.enums.fines.FineType;

import java.time.LocalDate;

public class CreatedFinesResponse {

    private int id;
    private double amount;
    private LocalDate dueDate;
    private boolean isPaid;
    private FineType fineType;
    private int reservationId;
    private String memberName;     // Üye adı
    private String bookTitle;      // Kitap başlığı


    public CreatedFinesResponse() {
    }


    public CreatedFinesResponse(int id, double amount, LocalDate dueDate, boolean isPaid, FineType fineType, int reservationId, String memberName, String bookTitle) {
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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public FineType getFineType() {
        return fineType;
    }

    public void setFineType(FineType fineType) {
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
