package com.example.yolarkadasim.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    private int rezervasyon_id;

    private int kullanici_id;

    private int seyahat_id;

    private int yolcu_sayisi;

    private Date rezervasyon_tarihi;

    private boolean durum;
}
