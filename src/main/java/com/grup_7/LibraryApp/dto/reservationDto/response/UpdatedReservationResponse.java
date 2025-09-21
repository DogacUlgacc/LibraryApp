package com.grup_7.LibraryApp.dto.reservationDto.response;

import java.time.LocalDate;

public class UpdatedReservationResponse {
    private int id;
    private LocalDate expireAt;
    private String status;

    public UpdatedReservationResponse() {
    }

    public UpdatedReservationResponse(int id, LocalDate expireAt, String status) {
        this.id = id;
        this.expireAt = expireAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

