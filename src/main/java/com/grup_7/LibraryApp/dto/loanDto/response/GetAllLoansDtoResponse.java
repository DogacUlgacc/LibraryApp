package com.grup_7.LibraryApp.dto.loanDto.response;

import java.time.LocalDate;

public class GetAllLoansDtoResponse {
    private int id;
    private int memberId;
    private int bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private int staffId;

    public GetAllLoansDtoResponse() { }

    public GetAllLoansDtoResponse(int id, int memberId, int bookId,
                                  LocalDate loanDate, LocalDate dueDate,
                                  LocalDate returnDate, int staffId) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.staffId = staffId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }
}
