package com.grup_7.LibraryApp.dto.finesDto.request;

import java.time.LocalDate;

public class UpdateFinesRequest {
    private double amount;
    private LocalDate dueDate;
    private boolean isPaid;       // ödeme yapılmış mı
    private String fineType;

    public UpdateFinesRequest() {
    }


    public UpdateFinesRequest(Double amount, LocalDate dueDate, Boolean isPaid, String fineType){
        this.amount = amount;
        this.dueDate = dueDate;
        this.fineType = fineType;
        this.isPaid = isPaid;
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

    public String getFineType() {
        return fineType;
    }

    public void setFineType(String fineType) {
        this.fineType = fineType;
    }
}
