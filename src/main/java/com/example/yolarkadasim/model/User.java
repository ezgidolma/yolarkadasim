package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


import java.time.LocalDate;

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

    private LocalDate kayitTarihi=LocalDate.now();

    private String profilResmi;


}
