package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Message;
import com.example.yolarkadasim.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesajlar")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getMessages(){
        return messageService.getMessagges();
    }

    @GetMapping("/{mesaj_id}")
    public Optional<Message> getMessageById(@PathVariable Integer mesaj_id){
        return messageService.getMessaggeById(mesaj_id);
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }

    @PutMapping
    public Message updateMessage(@RequestBody Message message){
        return messageService.updateMessage(message);
    }

    @DeleteMapping("/{mesaj_id}")
    public  void deleteMessage(@PathVariable Integer mesaj_id){
        messageService.deleteMessage(mesaj_id);
    }
}
