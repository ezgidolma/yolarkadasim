package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    private int mesaj_id;

    private int gonderen_id;

    private int alici_id;

    private String mesaj;

    private Date tarih;
}
