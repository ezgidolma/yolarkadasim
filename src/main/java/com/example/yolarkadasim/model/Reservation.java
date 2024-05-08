package com.example.yolarkadasim.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    private String rezervasyonId;

    private String kullaniciId;

    private String seyahatId;

    private int yolcuSayisi;

    private LocalDate rezervasyonTarihi;

    private boolean durum;
}
