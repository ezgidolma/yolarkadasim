package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Trip;
import com.example.yolarkadasim.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping()
    public Trip addTrip(@RequestBody Trip trip){
        return tripService.addTrip(trip);
    }

    @PutMapping("/{seyahatId}")
    public Trip updateTrip(@PathVariable Integer seyahatId,@RequestBody Trip trip){
        return tripService.updateTrip(seyahatId,trip);
    }

    @DeleteMapping("/{seyahatId}")
    public void deleteTrip(@PathVariable Integer seyahatId){
         tripService.deleteTrip(seyahatId);
    }
}
