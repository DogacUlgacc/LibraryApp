package com.grup_7.LibraryApp.dto.LoanDto.request;

import java.time.LocalDate;

public class UpdateLoanDtoRequest {
    private LocalDate dueDate;
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

