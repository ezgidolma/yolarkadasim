package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    private int seyahat_id;

    private int surucu_id;

    private String baslangic_noktasi;

    private String bitis_noktasi;

    private LocalDate tarih;

    private int bos_koltuk_sayisi;

    private float ucret;

    private boolean hayvan_durumu;

    private boolean sigara_durumu;
}
