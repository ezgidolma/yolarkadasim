package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  {

    @Id
    private String kullaniciId;

    private String ad;

    private String soyad;

    @Indexed(unique = true)
    private String eposta;

    private String sifre;

    private LocalDateTime kayitTarihi=LocalDateTime.now();

    private String profilResmi;


}
