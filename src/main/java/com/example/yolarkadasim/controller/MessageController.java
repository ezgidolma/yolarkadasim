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

    @GetMapping("/{mesajId}")
    public Optional<Message> getMessageById(@PathVariable Integer mesajId){
        return messageService.getMessaggeById(mesajId);
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }

    @PutMapping("/{mesjId}")
    public Message updateMessage(@PathVariable Integer mesajId,@RequestBody Message message){
        return messageService.updateMessage(mesajId,message);
    }

    @DeleteMapping("/{mesjId}")
    public  void deleteMessage(@PathVariable Integer mesajId){
        messageService.deleteMessage(mesajId);
    }
}
