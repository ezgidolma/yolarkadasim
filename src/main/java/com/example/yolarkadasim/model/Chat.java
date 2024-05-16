package com.example.yolarkadasim.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    private String chatId;
    private String gonderenId;
    private String aliciId;
    private Date tarih;
    private String icerik;

}
