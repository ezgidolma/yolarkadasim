package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Reservation;
import com.example.yolarkadasim.service.ReservationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rezervasyonlar")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }

    @GetMapping("/{rezervasyonId}")
    public Optional<Reservation> getReservationById(@PathVariable String rezervasyonId){
        return reservationService.getReservationById(rezervasyonId);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation rezervazsyon){
        return reservationService.addReservation(rezervazsyon);
    }

    @PutMapping
    public Reservation updateReservation(@RequestBody Reservation rezervazsyon){
        return reservationService.updateReservation(rezervazsyon);
    }

    @DeleteMapping("/{rezervasyonId}")
    public void deleteReservation(@PathVariable String rezervasyonId){
        reservationService.deleteReservation(rezervasyonId);
    }
}
