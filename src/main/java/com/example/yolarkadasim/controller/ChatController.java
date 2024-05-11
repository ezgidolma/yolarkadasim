package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.Chat;
import com.example.yolarkadasim.model.Message;
import com.example.yolarkadasim.repository.ChatRepository;
import com.example.yolarkadasim.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@Controller
@RequestMapping("/api")
public class ChatController {
   @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository mesajRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/sohbetler/{id}")
    public Chat getSohbetById(@PathVariable String id) {
        return chatRepository.findById(id).orElse(null);
    }

    @PostMapping("/mesajlar/{sohbetId}")
    public Message mesajGönder(@PathVariable String sohbetId, @RequestBody Message mesaj) {
        mesaj.setSohbetId(sohbetId);
        mesaj.setOlusturulmaTarihi(LocalDate.now());
        mesajRepository.save(mesaj);

        // WebSocket üzerinden mesajı gönder
        messagingTemplate.convertAndSend("/topic/chat/" + sohbetId, mesaj);

        return mesaj;
    }

    @MessageMapping("/send/{sohbetId}")
    public void websocketMesajGönder(@Payload Message mesaj) {
        mesaj.setOlusturulmaTarihi(LocalDate.now());
        mesajRepository.save(mesaj);

        // WebSocket üzerinden mesajı gönder
        messagingTemplate.convertAndSend("/topic/chat/" + mesaj.getSohbetId(), mesaj);
    }
}
