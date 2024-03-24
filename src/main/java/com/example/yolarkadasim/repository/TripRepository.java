package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TripRepository extends MongoRepository<Trip,Integer> {

}
