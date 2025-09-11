package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.AuthorDto.response.GetAllAuthorsResponse;
import com.grup_7.LibraryApp.dto.LoanDto.response.GetAllLoansDtoResponse;
import com.grup_7.LibraryApp.repository.BookRepository;
import com.grup_7.LibraryApp.repository.LoanRepository;
import com.grup_7.LibraryApp.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

     //TODO private final StaffRepository staffRepository;

    public LoanService(LoanRepository loanRepository,
                       MemberRepository memberRepository,
                       BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;

    }







}
