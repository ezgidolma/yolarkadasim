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

    @GetMapping("/{rezervasyon_id}")
    public Optional<Reservation> getReservationById(@PathVariable String rezervasyon_id){
        return reservationService.getReservationById(rezervasyon_id);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservationService.addReservation(reservation);
    }

    @PutMapping
    public Reservation updateReservation(@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/{rezervasyon_id}")
    public void deleteReservation(@PathVariable String rezervasyon_id){
        reservationService.deleteReservation(rezervasyon_id);
    }
}
