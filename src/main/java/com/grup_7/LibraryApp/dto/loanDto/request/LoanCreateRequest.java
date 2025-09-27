package com.grup_7.LibraryApp.dto.loanDto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class LoanCreateRequest {
    @NotNull
    private Long memberId;

    @NotNull
    private Long bookId;

    @PastOrPresent
    private LocalDate loanDate;

    @NotNull
    private int staffId;

    @NotNull
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(@NotNull int staffId) {
        this.staffId = staffId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }
}
