package com.example.yolarkadasim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    private String seyahatId;

    private String surucuId;

    private String baslangicNoktasi;

    private String bitisNoktasi;

    private LocalDate tarih;

    private LocalTime saat;

    private int bosKoltukSayisi;

    private float ucret;

    private boolean hayvanDurumu;

    private boolean sigaraDurumu;
}
