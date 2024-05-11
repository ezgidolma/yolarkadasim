package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    private String mesajId;

    private String sohbetId;

    private String aliciId;

    private String gonderenId;

    private String metin;

    private LocalDate olusturulmaTarihi;
}
