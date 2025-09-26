package com.grup_7.LibraryApp.repository;


import com.grup_7.LibraryApp.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Integer> {

    // Sadece memberId’ye göre cezaları getir
    List<Fine> findByReservationMemberMemberId(int memberId);

    // hem memberId hem paid durumuna göre
    List<Fine> findByReservationMemberMemberIdAndIsPaid(int memberId, boolean isPaid);

    // Ödenmemiş ceza var mı kontrol
    boolean existsByReservationMemberMemberIdAndIsPaidFalse(int memberId);

    boolean existsByReservation_Member_MemberIdAndIsPaidFalse(Integer memberId);

}