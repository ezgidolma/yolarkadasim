package com.example.yolarkadasim.service;

import com.example.yolarkadasim.model.Message;
import com.example.yolarkadasim.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {


    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagges(){
        return messageRepository.findAll();
    }

    public Optional<Message> getMessaggeById(String id){
        return messageRepository.findById(id);
    }

    public Message addMessage(Message mesaj){
        return  messageRepository.save(mesaj);
    }

    public Message updateMessage(Message mesaj){
        Optional<Message> optionalMessage = messageRepository.findById(mesaj.getMesajId());

        if (optionalMessage.isPresent()){
            Message currentMessage = optionalMessage.get();

            currentMessage.setIcerik(mesaj.getIcerik());
            currentMessage.setOlusturulmaTarihi(mesaj.getOlusturulmaTarihi());
            currentMessage.setAliciId(mesaj.getAliciId());
            currentMessage.setGonderenId(mesaj.getGonderenId());

            return messageRepository.save(currentMessage);
        }
        else {
            return null;
        }
    }

    public void deleteMessage(String id){
        messageRepository.deleteById(id);
    }
}
