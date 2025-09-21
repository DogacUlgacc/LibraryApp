package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.reservationDto.request.CreateReservationRequest;
import com.grup_7.LibraryApp.dto.reservationDto.response.ReservationResponse;
import com.grup_7.LibraryApp.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody CreateReservationRequest request) {
        ReservationResponse response = reservationService.createReservation(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable int id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fulfill-next")
    public ResponseEntity<Void> fulfillNextReservation(@RequestParam int bookId) {
        reservationService.fulfillNextForBook(bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByMember(@PathVariable int memberId) {
        List<ReservationResponse> list = reservationService.getReservationsByMember(memberId);
        return ResponseEntity.ok(list);
    }
}
