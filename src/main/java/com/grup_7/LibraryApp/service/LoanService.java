package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import com.grup_7.LibraryApp.dto.loanDto.request.CreateLoanDtoRequest;
import com.grup_7.LibraryApp.dto.loanDto.request.UpdateLoanDtoRequest;
import com.grup_7.LibraryApp.dto.loanDto.response.CreatedLoanResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.GetAllLoansDtoResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.GetLoanByIdDtoResponse;
import com.grup_7.LibraryApp.dto.loanDto.response.UpdatedLoanResponse;
import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Loan;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.entity.Staff;
import com.grup_7.LibraryApp.repository.BookRepository;
import com.grup_7.LibraryApp.repository.LoanRepository;
import com.grup_7.LibraryApp.repository.MemberRepository;
import com.grup_7.LibraryApp.repository.StaffRepository;
import com.grup_7.LibraryApp.rules.MemberBusinessRules;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final StaffRepository staffRepository;
    private final MemberBusinessRules memberBusinessRules;

    public LoanService(LoanRepository loanRepository, MemberRepository memberRepository, BookRepository bookRepository, StaffRepository staffRepository, MemberBusinessRules memberBusinessRules) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.staffRepository = staffRepository;
        this.memberBusinessRules = memberBusinessRules;
    }

    public List<GetAllLoansDtoResponse> getAllLoans() {
        return loanRepository.findAll().stream().map(l -> new GetAllLoansDtoResponse(Math.toIntExact(l.getId()), l.getMember().getMemberId(), l.getBook().getId(), l.getLoanDate(), l.getDueDate(), l.getReturnDate(), l.getStaff() != null ? l.getStaff().getId() : 0)).toList();
    }

    public GetLoanByIdDtoResponse getLoanById(int id) {
        Loan l = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Ödünç kaydı bulunamadı."));
        return new GetLoanByIdDtoResponse(Math.toIntExact(l.getId()), l.getMember().getMemberId(), l.getBook().getId(), l.getLoanDate(), l.getDueDate(), l.getReturnDate(), l.getStaff() != null ? l.getStaff().getId() : 0);
    }

    public CreatedLoanResponse createLoan(CreateLoanDtoRequest dto) {
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new BusinessException("Üye bulunamadı."));
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new BusinessException("Kitap bulunamadı."));

        memberBusinessRules.checkLoanLimit(member);

        Integer available = book.getAvailableCopies();
        if (available == null || available <= 0) {
            throw new RuntimeException("Uygun kopya yok.");
        }

        LocalDate loanDate = dto.getLoanDate() != null ? dto.getLoanDate() : LocalDate.now();
        LocalDate dueDate = dto.getDueDate() != null ? dto.getDueDate() : loanDate.plusDays(14);

        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(loanDate);
        loan.setDueDate(dueDate);
        loan.setReturnDate(null);

        if (dto.getStaffId() > 0) {
            Staff staff = staffRepository.findById(dto.getStaffId()).orElseThrow(() -> new RuntimeException("Personel bulunamadı."));
            loan.setStaff(staff);
        }

        book.setAvailableCopies(available - 1);
        bookRepository.save(book);

        Loan saved = loanRepository.save(loan);

        return new CreatedLoanResponse(Math.toIntExact(saved.getId()), saved.getMember().getMemberId(), saved.getBook().getId(), saved.getLoanDate(), saved.getDueDate(), saved.getReturnDate(), saved.getStaff() != null ? saved.getStaff().getId() : 0);
    }

    public UpdatedLoanResponse updateLoan(int id, UpdateLoanDtoRequest dto) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Ödünç kaydı bulunamadı."));

        if (dto.getReturnDate() != null) {
            if (loan.getReturnDate() != null) {
                throw new RuntimeException("Kayıt zaten iade edilmiş.");
            }
            loan.setReturnDate(dto.getReturnDate());

            Book book = loan.getBook();
            Integer available = book.getAvailableCopies();
            book.setAvailableCopies((available == null ? 0 : available) + 1);
            bookRepository.save(book);
        }

        if (dto.getDueDate() != null) {
            if (loan.getReturnDate() != null) {
                throw new RuntimeException("İade edilmiş kayıt için uzatma yapılamaz.");
            }
            if (loan.getDueDate() != null && !dto.getDueDate().isAfter(loan.getDueDate())) {
                throw new RuntimeException("Yeni dueDate mevcut dueDate'ten sonra olmalıdır.");
            }
            loan.setDueDate(dto.getDueDate());
        }

        if (dto.getStaffId() > 0) {
            Staff staff = staffRepository.findById(dto.getStaffId()).orElseThrow(() -> new RuntimeException("Personel bulunamadı."));
            loan.setStaff(staff);
        }

        Loan saved = loanRepository.save(loan);

        return new UpdatedLoanResponse(Math.toIntExact(saved.getId()), saved.getMember().getMemberId(), saved.getBook().getId(), saved.getLoanDate(), saved.getDueDate(), saved.getReturnDate(), saved.getStaff() != null ? saved.getStaff().getId() : 0);
    }

    public void deleteLoan(int id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Ödünç kaydı bulunamadı."));

        if (loan.getReturnDate() == null) {
            Book book = loan.getBook();
            Integer available = book.getAvailableCopies();
            book.setAvailableCopies((available == null ? 0 : available) + 1);
            bookRepository.save(book);
        }

        loanRepository.delete(loan);
    }


}
