package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Message;
import com.example.yolarkadasim.model.ChatMessage;
import com.example.yolarkadasim.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesajlar")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private  SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat.selectUser")
    public void selectUser(@Payload String selectedUser, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("selectedUser", selectedUser);
    }

    @MessageMapping("/chat.sendToSelectedUser")
    public void sendMessageToSelectedUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String selectedUser = (String) headerAccessor.getSessionAttributes().get("selectedUser");
        messagingTemplate.convertAndSendToUser(selectedUser, "/queue/private", chatMessage);
    }


    @GetMapping
    public List<Message> getMessages(){
        return messageService.getMessagges();
    }

    @GetMapping("/{mesaj_id}")
    public Optional<Message> getMessageById(@PathVariable String mesaj_id){
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
    public  void deleteMessage(@PathVariable String mesaj_id){
        messageService.deleteMessage(mesaj_id);
    }
}
