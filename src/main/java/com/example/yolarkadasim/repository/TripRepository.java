package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends MongoRepository<Trip,String> {

    @Query("{'baslangic_noktasi': ?0, 'bitis_noktasi': ?1, 'tarih': ?2, 'bos_koltuk_sayisi': {$gte: ?3}}")
    List<Trip> findFilteredTrips(String baslangic_noktasi, String bitis_noktasi, LocalDate tarih, int kisi_sayisi);
}
