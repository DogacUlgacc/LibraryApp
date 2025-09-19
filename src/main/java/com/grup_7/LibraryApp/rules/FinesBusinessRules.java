package com.grup_7.LibraryApp.rules;

import com.grup_7.LibraryApp.enums.fines.FineType;
import com.grup_7.LibraryApp.repository.FinesRepository;
import com.grup_7.LibraryApp.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class FinesBusinessRules {

    private static final double DAILY_LATE_FEE_RATE = 5.0; // Gecikme cezası günlük oranı
    private static final double LOST_DAMAGE_PROCESSING_FEE = 15.0; // Kayıp/hasar sabit ceza

    private final FinesRepository finesRepository;
    private final ReservationRepository reservationRepository;

    public FinesBusinessRules(FinesRepository finesRepository, ReservationRepository reservationRepository) {
        this.finesRepository = finesRepository;
        this.reservationRepository = reservationRepository;
    }


    // Rezervasyon var mı

    public void checkReservationExists(int reservationId) {
        boolean exists = reservationRepository.existsById(reservationId);
        if (!exists) {
            throw new RuntimeException("Rezervasyon bulunamadı: " + reservationId);
        }
    }


    // Üyenin ödenmemiş cezası var mı

    public void checkMemberHasNoUnpaidFines(int memberId) {
        boolean hasUnpaid = finesRepository.existsByReservationMemberIdAndIsPaidFalse(memberId);
        if (hasUnpaid) {
            throw new RuntimeException("Üyenin ödenmemiş cezası var, yeni işlem yapılamaz: " + memberId);
        }
    }


    // Gecikme ,kayıp, hasar cezası hesaplama

    public double calculateFineAmount(FineType fineType, LocalDate returnDate, LocalDate dueDate, double bookPrice) {

        if (fineType == FineType.DELAY) {
            if (returnDate == null || dueDate == null || !returnDate.isAfter(dueDate)) {
                return 0.0; // gecikme yok
            }
            long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);
            return overdueDays * DAILY_LATE_FEE_RATE;

        } else if (fineType == FineType.LOST || fineType == FineType.DAMAGE) {
            // Kayıp veya hasarlı kitap cezası
            return bookPrice + LOST_DAMAGE_PROCESSING_FEE;

        } else {
            return 0.0;
        }
    }

    // Üye yeni kitap alabilir mi veya rezervasyon yapabilir mi
    public boolean canMemberBorrowOrReserve(int memberId) {
        return !finesRepository.existsByReservationMemberIdAndIsPaidFalse(memberId);
    }
}
