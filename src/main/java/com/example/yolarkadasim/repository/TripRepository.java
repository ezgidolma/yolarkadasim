package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends MongoRepository<Trip,String> {

    @Query("{'baslangicNoktasi': ?0, 'bitisNoktasi': ?1, 'tarih': ?2, 'bosKoltukSayisi': {$gte: ?3}}")
    List<Trip> findFilteredTrips(String baslangicNoktasi, String bitisNoktasi, LocalDate tarih, int bosKoltukSayisi);
}
