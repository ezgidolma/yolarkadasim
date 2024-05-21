package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/api")
public class ChatController {

    @MessageMapping("/chat/{userId}") // Kullanıcının kendi ID'sine özel kanala göndermek için userId parametresi alıyoruz
    @SendTo("/chat/{userId}/messages") // Kullanıcının kendi ID'sine özel kanala gönderiyoruz
    public Chat sendMessage(@Payload Chat chatMessage) {
        chatMessage.setTarih(new Date()); // Şuanki zamanı ayarla
        return chatMessage;
    }


}
