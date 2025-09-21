package com.grup_7.LibraryApp.repository;

import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.entity.Reservation;
import com.grup_7.LibraryApp.enums.reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    Optional<Object> findTopByBookAndStatusOrderByReservationDateAsc(Book book, ReservationStatus reservationStatus);

    List<Reservation> findByMember(Member member);

    Optional<Reservation> findByMemberAndBookAndStatus(Member member, Book book, ReservationStatus reservationStatus);
}
