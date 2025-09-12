package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.LoanDto.request.CreateLoanDtoRequest;
import com.grup_7.LibraryApp.dto.LoanDto.request.UpdateLoanDtoRequest;
import com.grup_7.LibraryApp.dto.LoanDto.response.CreatedLoanResponse;
import com.grup_7.LibraryApp.dto.LoanDto.response.GetAllLoansDtoResponse;
import com.grup_7.LibraryApp.dto.LoanDto.response.GetLoanByIdDtoResponse;
import com.grup_7.LibraryApp.dto.LoanDto.response.UpdatedLoanResponse;
import com.grup_7.LibraryApp.service.LoanService;
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
        return loanService.getAllLoans();
    }

    @GetMapping("{id}")
    public GetLoanByIdDtoResponse getByIdLoan(@PathVariable int id){
        return loanService.getLoanById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLoanResponse createLoan(@RequestBody CreateLoanDtoRequest request) {
        return loanService.createLoan(request);
    }

    @PutMapping("{id}")
    public UpdatedLoanResponse updateLoan(@PathVariable int id,
                                      @RequestBody UpdateLoanDtoRequest request) {
        return loanService.updateLoan(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        loanService.deleteLoan(id);
    }


}
