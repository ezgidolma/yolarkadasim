package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation,Integer> {

}
