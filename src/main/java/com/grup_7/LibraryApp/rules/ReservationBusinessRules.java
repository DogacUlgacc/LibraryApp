package com.grup_7.LibraryApp.rules;

import com.grup_7.LibraryApp.entity.Book;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.entity.Reservation;
import com.grup_7.LibraryApp.enums.member.MembershipLevel;
import com.grup_7.LibraryApp.enums.reservation.ReservationStatus;
import com.grup_7.LibraryApp.repository.ReservationRepository;
import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationBusinessRules {

    private final ReservationRepository reservationRepository;

    public ReservationBusinessRules(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public void checkMemberCanReserve(Member member, Book book) {
        checkMemberEligibility(member);
        checkBookAvailability(book);
        checkDuplicateReservation(member, book);
    }

    private void checkMemberEligibility(Member member) {
        if (member.getMembershipLevel() == MembershipLevel.BANNED) {
            throw new BusinessException("BANNED üyeler rezervasyon yapamaz.");
        }
    }

    private void checkBookAvailability(Book book) {
        if (book.getAvailableCopies() > 0) {
            throw new BusinessException("Kitap stokta mevcut. Rezervasyon yerine ödünç alınmalıdır.");
        }
    }

    private void checkDuplicateReservation(Member member, Book book) {
        Optional<Reservation> activeReservation =
                reservationRepository.findByMemberAndBookAndStatus(member, book, ReservationStatus.ACTIVE);

        if (activeReservation.isPresent()) {
            throw new BusinessException("Bu üyenin aynı kitap için aktif rezervasyonu zaten var.");
        }
    }


    public void assignPickupIfBookAvailable(Book book) {
        if (book.getAvailableCopies() <= 0) return;

        reservationRepository.findTopByBookAndStatusOrderByReservationDateAsc(book, ReservationStatus.ACTIVE)
                .ifPresent(reservation -> {
                    reservation.setExpireAt(LocalDate.now().plusDays(1));
                    reservationRepository.save(reservation);
                });
    }

    public void fulfillNextForBook(Book book) {
        reservationRepository.findTopByBookAndStatusOrderByReservationDateAsc(book, ReservationStatus.ACTIVE)
                .ifPresent(reservation -> {
                    reservation.setExpireAt(LocalDate.now().plusDays(1));
                    reservationRepository.save(reservation);
                });
    }
}
