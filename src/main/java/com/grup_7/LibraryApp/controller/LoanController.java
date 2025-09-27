package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.loanDto.request.LoanCreateRequest;
import com.grup_7.LibraryApp.dto.loanDto.request.LoanReturnRequest;
import com.grup_7.LibraryApp.dto.loanDto.response.*;
import com.grup_7.LibraryApp.enums.loan.LoanStatus;
import com.grup_7.LibraryApp.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    private final LoanService loanService;

    @GetMapping("/all")
    public List<GetAllLoansDtoResponse> getAllLoans(){
        return loanService.getList();
    }

    @GetMapping("members/{memberId}")
    public List<GetAllLoansDtoResponse> getLoansByMemberAndStatus(@PathVariable int memberId,
                                                                @RequestParam(required = false) LoanStatus status){
        return loanService.getLoansByMemberAndStatus(memberId,status);
    }

    //TODO:: BURDA PARAMETRESİZ OLARAK MEMBER_ID ile olan methodu çalıştır şuan aynı olduğu için hata veriyor düzeltilecek!
    /*@GetMapping("/members/{memberId}")
    public List<GetAllLoansDtoResponse> getLoansByMember(@PathVariable int memberId){
     return loanService.getLoansForMember(memberId);
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLoanResponse createLoan(@Valid @RequestBody LoanCreateRequest request) {
        return loanService.add(request);
    }

    @PutMapping("{id}")
    public ReturnedLoanResponse updateLoan(@Valid @RequestBody LoanReturnRequest request) {
        return loanService.returnLoan(request);
    }

    @DeleteMapping("{id}")
    public DeletedLoanResponse delete(@Valid @PathVariable int id) {
         return loanService.delete(id);
    }


}
