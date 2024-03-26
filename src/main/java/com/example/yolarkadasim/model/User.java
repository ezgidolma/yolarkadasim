package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private int kullanici_id;

    private String ad;

    private String soyad;

    private String eposta;

    private String sifre;

    private Date dogum_tarihi;

    private Date kayit_tarihi;

    private String profil_resmi;

    private String bildirimler;

}
