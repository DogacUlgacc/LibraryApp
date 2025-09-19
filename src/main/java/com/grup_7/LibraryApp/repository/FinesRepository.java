package com.grup_7.LibraryApp.repository;


import com.grup_7.LibraryApp.entity.Fines;
import com.grup_7.LibraryApp.enums.fines.FineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinesRepository extends JpaRepository<Fines,Integer> {

    List<Fines> findByReservationMemberId(int memberId);

    List<Fines> findByReservationMemberIdAndIsPaidFalse(int memberId);

    boolean existsByReservationMemberIdAndIsPaidFalse(int memberId);

    boolean existsByReservationMemberIdAndFineTypeAndIsPaidFalse(int memberId, FineType fineType);

    Fines findTopByReservationMemberIdAndIsPaidFalseOrderByAmountDesc(int memberId);

}
