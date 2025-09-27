package com.grup_7.LibraryApp.repository;

import com.grup_7.LibraryApp.entity.Loan;
import com.grup_7.LibraryApp.enums.loan.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    long countByMember_MemberIdAndReturnDateIsNull(Integer memberId);

    long countByMember_MemberIdAndStatusIn(Integer memberId, Collection<LoanStatus> statuses);

    boolean existsByMember_MemberIdAndBook_IdAndStatusIn(Integer memberId, Integer bookId, Collection<LoanStatus> statuses);

    Optional<Loan> findByIdAndStatusIn(Integer id, Collection<LoanStatus> statuses);

    List<Loan> findByMember_MemberId(Integer memberId);

    List<Loan> findByMember_MemberIdAndStatus(Integer memberId, LoanStatus status);

    @Query("select count(l) from Loan l where l.member.memberId = :memberId and l.returnDate is null")
    long countActiveLoansByReturnDateNull(Integer memberId);

    List<Loan> findByMember_MemberIdAndStatus(int memberId, LoanStatus status);
    List<Loan> findByMember_MemberId(int memberId);

}
