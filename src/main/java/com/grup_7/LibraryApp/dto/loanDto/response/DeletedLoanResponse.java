package com.grup_7.LibraryApp.dto.loanDto.response;


public class DeletedLoanResponse {
    private Integer id;

    public DeletedLoanResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
