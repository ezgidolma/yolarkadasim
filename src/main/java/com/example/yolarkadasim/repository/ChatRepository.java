package com.example.yolarkadasim.repository;

import com.example.yolarkadasim.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {
}
