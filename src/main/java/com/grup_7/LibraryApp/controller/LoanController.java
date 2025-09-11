package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    private final LoanService loanService;

    //TODO : HTTP ISTEKLERI

}
