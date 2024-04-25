package com.example.yolarkadasim.service;

import com.example.yolarkadasim.model.Message;
import com.example.yolarkadasim.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    @Autowired
    public MessageRepository messageRepository;

    public List<Message> getMessagges(){
        return messageRepository.findAll();
    }

    public Optional<Message> getMessaggeById(String id){
        return messageRepository.findById(id);
    }

    public Message addMessage(Message message){
        return  messageRepository.save(message);
    }

    public Message updateMessage(Message message){
        Optional<Message> optionalMessage = messageRepository.findById(message.getMesaj_id());

        if (optionalMessage.isPresent()){
            Message currentMessage = optionalMessage.get();

            currentMessage.setMesaj(message.getMesaj());
            currentMessage.setTarih(message.getTarih());
            currentMessage.setAlici_id(message.getAlici_id());
            currentMessage.setGonderen_id(message.getGonderen_id());

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
