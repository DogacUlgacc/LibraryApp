package com.grup_7.LibraryApp.dto.loanDto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class UpdateLoanDtoRequest {

    @Future
    private LocalDate dueDate;
    @PastOrPresent
    private LocalDate returnDate;

    private int staffId;

    public UpdateLoanDtoRequest() { }

    public UpdateLoanDtoRequest(LocalDate dueDate, LocalDate returnDate, int staffId) {
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.staffId = staffId;
    }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }
}

