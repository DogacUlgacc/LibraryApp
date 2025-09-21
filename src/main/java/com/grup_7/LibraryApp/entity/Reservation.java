package com.grup_7.LibraryApp.entity;


import com.grup_7.LibraryApp.enums.reservation.ReservationStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations" ,
              uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "book_id", "status"}))
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "expire_at")
    private LocalDate expireAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;

    public Reservation() {}

    public Reservation(int id, Member member, Book book, LocalDate reservationDate, LocalDate expireAt, ReservationStatus status) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.reservationDate = reservationDate;
        this.expireAt = expireAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }


}
