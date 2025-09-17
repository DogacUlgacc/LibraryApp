package com.grup_7.LibraryApp.dto.loanDto.request;

import java.time.LocalDate;

public class CreateLoanDtoRequest {
    private int memberId;
    private int bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private int staffId;

    public CreateLoanDtoRequest() { }

    public CreateLoanDtoRequest(int memberId, int bookId, LocalDate loanDate, LocalDate dueDate, int staffId) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.staffId = staffId;
    }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }
}
