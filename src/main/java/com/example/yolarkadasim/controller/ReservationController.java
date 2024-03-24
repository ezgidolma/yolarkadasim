package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Reservation;
import com.example.yolarkadasim.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rezervasyonlar")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }

    @GetMapping("/{rezervasyonId}")
    public Optional<Reservation> getReservationById(@PathVariable Integer rezervasyonId){
        return reservationService.getReservationById(rezervasyonId);
    }

    @PostMapping()
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/{rezervasyonId}")
    public Reservation updateReservation(@PathVariable Integer rezervasyonId,@RequestBody Reservation reservation){
        return reservationService.updateReservation(rezervasyonId,reservation);
    }

    @DeleteMapping()
    public void deleteReservation(@PathVariable Integer rezervasyonId){
        reservationService.deleteReservation(rezervasyonId);
    }
}
