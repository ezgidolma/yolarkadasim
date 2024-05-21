package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Message;
import com.example.yolarkadasim.service.MessageService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/mesajlar")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages(){
        return messageService.getMessagges();
    }

    @GetMapping("/{mesajId}")
    public Optional<Message> getMessageById(@PathVariable String mesajId){
        return messageService.getMessaggeById(mesajId);
    }

    @PostMapping
    public Message addMessage(@RequestBody Message mesaj){
        return messageService.addMessage(mesaj);
    }

    @PutMapping
    public Message updateMessage(@RequestBody Message mesaj){
        return messageService.updateMessage(mesaj);
    }

    @DeleteMapping("/{mesajId}")
    public  void deleteMessage(@PathVariable String mesajId){
        messageService.deleteMessage(mesajId);
    }
}
