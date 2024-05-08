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
@RequestMapping("/api/seyahatler")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping
    public List<Trip> getTrips(){
        return tripService.getTrips();
    }
    @GetMapping("/{seyahatId}")
    public Optional<Trip> getTripById(@PathVariable String seyahatId){
        return tripService.getTripById(seyahatId);
    }

    @PostMapping
    public Trip addTrip(@RequestBody Trip seyahat){
        return tripService.addTrip(seyahat);
    }

    @PutMapping
    public Trip updateTrip(@RequestBody Trip seyahat){
        return tripService.updateTrip(seyahat);
    }

    @DeleteMapping("/{seyahatId}")
    public void deleteTrip(@PathVariable String seyahatId){
         tripService.deleteTrip(seyahatId);
    }

    @GetMapping("/arama")
    public List<Trip> searchTrip(@RequestParam("baslangicNoktasi") String baslangicNoktasi,
                                 @RequestParam("bitisNoktasi") String bitisNoktasi,
                                 @RequestParam("tarih")  @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate tarih,
                                 @RequestParam("bosKoltukSayisi") int bosKoltukSayisi)
    {
        return tripService.searchTrip(baslangicNoktasi,bitisNoktasi,tarih,bosKoltukSayisi);

    }

}
