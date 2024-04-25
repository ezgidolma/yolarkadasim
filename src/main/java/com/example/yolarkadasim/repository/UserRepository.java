package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEposta(String eposta);
}
