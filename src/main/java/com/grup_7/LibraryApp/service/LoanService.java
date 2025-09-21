package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.loanDto.request.LoanCreateRequest;
import com.grup_7.LibraryApp.dto.loanDto.request.LoanReturnRequest;
import com.grup_7.LibraryApp.dto.loanDto.response.CreatedLoanResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.DeletedLoanResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.GetAllLoansDtoResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.ReturnedLoanResponse;
import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Loan;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.enums.loan.LoanStatus;
import com.grup_7.LibraryApp.mapper.LoanMapper;
import com.grup_7.LibraryApp.repository.BookRepository;
import com.grup_7.LibraryApp.repository.LoanRepository;
import com.grup_7.LibraryApp.repository.MemberRepository;
import com.grup_7.LibraryApp.rules.LoanBusinessRules;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final LoanBusinessRules rules;
    private final LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository,
                       MemberRepository memberRepository,
                       BookRepository bookRepository,
                       LoanBusinessRules rules,
                       LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.rules = rules;
        this.loanMapper = loanMapper;
    }

    // === LIST ===
    public List<GetAllLoansDtoResponse> getList() {
        List<Loan> loans = loanRepository.findAll();
        return loanMapper.toGetListLoanResponse(loans);
    }

    // === CREATE ===
    @Transactional
    public CreatedLoanResponse add(@Valid LoanCreateRequest request) {
        // 1) Kurallar (BANNED/limit, kitap aktif & stok>0, aynı kitap için açık loan yok)
        Member member = rules.memberMustBeBorrowEligibleWithoutFineCheck(request.getMemberId().intValue());
        Book book = rules.bookMustBeLoanable(request.getBookId().intValue());
        rules.mustNotHaveOpenLoanForSameBook(member.getMemberId(), book.getId());

        // 2) Loan nesnesi
        Loan loan = loanMapper.toLoan(request);
        LocalDate loanDate = (request.getLoanDate() != null) ? request.getLoanDate() : LocalDate.now();
        loan.setLoanDate(loanDate);
        loan.setMember(member);
        loan.setBook(book);
        loan.setStatus(LoanStatus.OPEN);
        loan.setDueDate(rules.calcDueDate(loanDate, member.getMembershipLevel()));

        // 3) Kaydet + stok düş
        loan = loanRepository.save(loan);
        rules.decreaseStockOne(book);

        return loanMapper.toCreatedLoanResponse(loan);
    }

    // === RETURN ===
    @Transactional
    public ReturnedLoanResponse returnLoan(@Valid LoanReturnRequest request) {
        Loan loan = rules.loanMustBeReturnable(request.getLoanId().intValue());
        LocalDate returnDate = (request.getReturnDate() != null) ? request.getReturnDate() : LocalDate.now();
        loan.setReturnDate(returnDate);

        // Fine devre dışı: gecikme cezası oluşturma yerine sadece LATE işaretleyip kapatacağız (TODO: Fine hazır olunca aç)
        rules.markLateIfNeeded(loan, returnDate); // sadece status=LATE işaretleyebilir
        loan.setStatus(LoanStatus.CLOSED);
        loan = loanRepository.save(loan);

        rules.increaseStockOne(loan.getBook());
        return loanMapper.toReturnedLoanResponse(loan);
    }

    @Transactional
    public DeletedLoanResponse delete(int id) {
        Loan loan = rules.loanMustExist(id);
        loanRepository.delete(loan);
        return new DeletedLoanResponse(id);
    }
}
