package com.grup_7.LibraryApp.dto.reservationDto.request;

import java.time.LocalDate;

public class UpdateReservationRequest {
    private LocalDate expireAt;
    private String status;

    public UpdateReservationRequest() {}

    public UpdateReservationRequest(LocalDate expireAt, String status) {
        this.expireAt = expireAt;
        this.status = status;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
