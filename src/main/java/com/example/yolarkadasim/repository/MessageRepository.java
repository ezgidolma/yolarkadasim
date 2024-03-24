package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message,Integer> {

}
