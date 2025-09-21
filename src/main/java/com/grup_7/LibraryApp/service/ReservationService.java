package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.reservationDto.request.CreateReservationRequest;
import com.grup_7.LibraryApp.dto.reservationDto.response.ReservationResponse;
import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.entity.Reservation;
import com.grup_7.LibraryApp.enums.reservation.ReservationStatus;
import com.grup_7.LibraryApp.mapper.ReservationMapper;
import com.grup_7.LibraryApp.repository.BookRepository;
import com.grup_7.LibraryApp.repository.MemberRepository;
import com.grup_7.LibraryApp.repository.ReservationRepository;
import com.grup_7.LibraryApp.rules.ReservationBusinessRules;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationBusinessRules reservationRules;

    public ReservationService(ReservationRepository reservationRepository,
                              MemberRepository memberRepository,
                              BookRepository bookRepository,
                              ReservationMapper reservationMapper,
                              ReservationBusinessRules reservationRules) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.reservationMapper = reservationMapper;
        this.reservationRules = reservationRules;
    }

    public ReservationResponse createReservation(CreateReservationRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        reservationRules.checkMemberCanReserve(member, book);

        Reservation reservation = reservationMapper.toReservationEntity(request);
        reservation.setMember(member);
        reservation.setBook(book);
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.setReservationDate(LocalDate.now());

        reservationRepository.save(reservation);

        reservationRules.assignPickupIfBookAvailable(book);

        return reservationMapper.toReservationResponse(reservation);
    }

    public List<ReservationResponse> getReservationsByMember(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));
        List<Reservation> reservations = reservationRepository.findByMember(member);
        return reservationMapper.toReservationListResponseList(reservations);
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı"));
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);

        reservationRules.fulfillNextForBook(reservation.getBook());
    }

    public void fulfillNextForBook(int bookId) {  Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        reservationRules.fulfillNextForBook(book);
    }
}
