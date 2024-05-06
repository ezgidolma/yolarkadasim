package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String gonderen;
    private String icerik;
    private TrayIcon.MessageType type;

    public enum MessageType{
        CHAT,LEAVE,JOIN
    }
}
