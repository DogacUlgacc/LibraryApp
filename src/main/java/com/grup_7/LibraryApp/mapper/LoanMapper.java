package com.grup_7.LibraryApp.mapper;

import com.grup_7.LibraryApp.dto.loanDto.request.LoanCreateRequest;
import com.grup_7.LibraryApp.dto.loanDto.request.LoanReturnRequest;
import com.grup_7.LibraryApp.dto.loanDto.response.CreatedLoanResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.GetAllLoansDtoResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.ReturnedLoanResponse;
import com.grup_7.LibraryApp.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface LoanMapper {

    //List
    List<GetAllLoansDtoResponse> toGetListLoanResponse(List<Loan> loanList);

    //Loan objesinden create'e
    Loan toLoan(LoanCreateRequest loanCreateRequest);

    //Create'den Loan'a
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "book.id",   target = "bookId")
    @Mapping(source = "staff.id", target = "staffId")
    CreatedLoanResponse toCreatedLoanResponse(Loan loan);

    //Loan objesinden update'e
    Loan toLoan(LoanReturnRequest loanReturnRequest);

    //Update'den Loan objesine
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "book.id",   target = "bookId")
    ReturnedLoanResponse toReturnedLoanResponse(Loan loan);
}
