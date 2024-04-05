package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Trip;
import com.example.yolarkadasim.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seyahatlar")
public class TripController {

    @Autowired
    private TripService tripService;


    @GetMapping
    public List<Trip> getTrips(){
        return tripService.getTrips();
    }
    @GetMapping("/{seyahat_id}")
    public Optional<Trip> getTripById(@PathVariable Integer seyahat_id){
        return tripService.getTripById(seyahat_id);
    }

    @PostMapping
    public Trip addTrip(@RequestBody Trip trip){
        return tripService.addTrip(trip);
    }

    @PutMapping
    public Trip updateTrip(@RequestBody Trip trip){
        return tripService.updateTrip(trip);
    }

    @DeleteMapping("/{seyahatId}")
    public void deleteTrip(@PathVariable Integer seyahatId){
         tripService.deleteTrip(seyahatId);
    }

    @GetMapping("/arama")
    public List<Trip> searchTrip(@RequestParam("baslangic_noktasi") String baslangic_noktasi,
                                                 @RequestParam("bitis_noktasi") String bitis_noktasi,
                                                 @RequestParam("tarih")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tarih,
                                                 @RequestParam("kisi_sayisi") int kisi_sayisi){
        return tripService.searchTrip(baslangic_noktasi,bitis_noktasi,tarih,kisi_sayisi);

    }

}
