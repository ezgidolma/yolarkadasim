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
public class Reservation {

    @Id
    private String rezervasyon_id;

    private String kullanici_id;

    private String seyahat_id;

    private int yolcu_sayisi;

    private LocalDate rezervasyon_tarihi;

    private boolean durum;
}
