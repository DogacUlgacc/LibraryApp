package com.grup_7.LibraryApp.dto.finesDto.request;

import java.time.LocalDate;

public class CreateFinesRequest {

    private double amount;        // Ceza miktarı
    private LocalDate dueDate;    // son ödeme tarihi
    private String fineType;      // Ceza tipi (DELAY, LOST, DAMAGE)
    private int reservationId;    // Hangi rezervasyon


    public CreateFinesRequest() {
    }



    public CreateFinesRequest(Double amount, LocalDate dueDate, String fineType, Integer reservationId) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.fineType = fineType;
        this.reservationId = reservationId;

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
}
