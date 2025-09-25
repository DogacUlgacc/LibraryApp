package com.grup_7.LibraryApp.rules;

import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Loan;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.enums.book.BookStatus;
import com.grup_7.LibraryApp.enums.loan.LoanStatus;
import com.grup_7.LibraryApp.enums.member.MembershipLevel;
import com.grup_7.LibraryApp.repository.BookRepository;
import com.grup_7.LibraryApp.repository.FineRepository;
import com.grup_7.LibraryApp.repository.LoanRepository;
import com.grup_7.LibraryApp.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class LoanBusinessRules {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final MemberBusinessRules memberBusinessRules;
    private final FineRepository fineRepository;
    private static final int STANDARD_DUE_DAYS = 14;
    private static final int GOLD_DUE_DAYS     = 21;
    private final BookBusinessRules bookBusinessRules;

    public LoanBusinessRules(MemberRepository memberRepository,
                             BookRepository bookRepository,
                             LoanRepository loanRepository,
                             MemberBusinessRules memberBusinessRules, FineRepository fineRepository, BookBusinessRules bookBusinessRules) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.memberBusinessRules = memberBusinessRules;
        this.fineRepository = fineRepository;
        this.bookBusinessRules = bookBusinessRules;
    }

    public Loan loanMustExist(Integer id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Loan bulunamadı: " + id));
    }

    public Loan loanMustBeReturnable(Integer id) {
        Loan loan = loanMustExist(id);
        if (!(loan.getStatus() == LoanStatus.OPEN || loan.getStatus() == LoanStatus.LATE)) {
            throw new BusinessException("Bu loan mevcut durumda iade edilemez.");
        }
        return loan;
    }

    // Fine kontrolü geçici olarak kapalı
    public Member memberMustBeBorrowEligibleWithoutFineCheck(Integer memberId) {
        Member m = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException("Member bulunamadı: " + memberId));

        // BANNED & limit: MemberBusinessRules'teki checkLoanLimit ile
        memberBusinessRules.checkLoanLimit(m);

        // TODO (FineRepository hazır olunca aç):
        boolean hasUnpaidFine = fineRepository.existsByReservation_Member_MemberIdAndIsPaidFalse(memberId);
        if (hasUnpaidFine) throw new BusinessException("Üyenin ödenmemiş cezası var.");


        return m;
    }

    public Book bookMustBeLoanable(Integer bookId) {
        Book b = bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Book bulunamadı: " + bookId));
        bookBusinessRules.bookMustBeActive(b);
        if (b.getStatus() == BookStatus.INACTIVE) {
            throw new BusinessException("INACTIVE kitap ödünç verilemez.");
        }
        if (b.getAvailableCopies() == 0 ||b.getAvailableCopies() <= 0) {
            throw new BusinessException("Stokta uygun kopya yok.");
        }
        return b;
    }

    public void mustNotHaveOpenLoanForSameBook(Integer memberId, Integer bookId) {
        boolean exists = loanRepository.existsByMember_MemberIdAndBook_IdAndStatusIn(
                memberId, bookId, List.of(LoanStatus.OPEN, LoanStatus.LATE));
        if (exists) throw new BusinessException("Bu üyenin aynı kitap için açık loan'ı var.");
    }

    public int dueDaysFor(MembershipLevel level) {
        return (level == MembershipLevel.GOLD) ? GOLD_DUE_DAYS : STANDARD_DUE_DAYS;
    }

    public LocalDate calcDueDate(LocalDate loanDate, MembershipLevel level) {
        return loanDate.plusDays(dueDaysFor(level));
    }

    // Fine yokken sadece geç kaldı bilgisini işaretleyelim (service CLOSED'a çeviriyor)
    public void markLateIfNeeded(Loan loan, LocalDate returnDate) {
        if (returnDate.isAfter(loan.getDueDate())) {
            long lateDays = ChronoUnit.DAYS.between(loan.getDueDate(), returnDate);
            if (lateDays > 0) {
                loan.setStatus(LoanStatus.LATE);
                // TODO: Fine hazır olunca burada gecikme cezası oluştur.
            }
        }
    }

    public void decreaseStockOne(Book b) {
        b.setAvailableCopies(b.getAvailableCopies() - 1);
        if (b.getAvailableCopies() < 0) throw new IllegalStateException("availableCopies < 0");
        bookRepository.save(b);
    }

    public void increaseStockOne(Book b) {
        b.setAvailableCopies(b.getAvailableCopies() + 1);
        if (b.getAvailableCopies() > b.getTotalCopies())
            throw new IllegalStateException("availableCopies > totalCopies");
        bookRepository.save(b);
    }
}
