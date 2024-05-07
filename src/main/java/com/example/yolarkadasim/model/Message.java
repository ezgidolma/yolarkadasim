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
    private String mesaj_id;

    private String gonderen_id;

    private String alici_id;

    private String mesaj;

    private LocalDate tarih;
}
