package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Integer> {

}
