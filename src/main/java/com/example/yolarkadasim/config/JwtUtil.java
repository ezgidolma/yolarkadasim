package com.example.yolarkadasim.config;

import com.example.yolarkadasim.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // Token geçerlilik süresi (örneğin, 1 gün)

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String generateToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .claim("kullaniciId",user.getKullaniciId())
                .claim("ad", user.getAd())
                .claim("soyad", user.getSoyad())
                .claim("eposta", user.getEposta())
                .claim("sifre", user.getSifre())
                .claim("dogumTarihi",user.getDogumTarihi() != null ? user.getDogumTarihi().toString() : null) // Tarih alanını kontrol et ve uygun formata dönüştür
                .claim("profilResmi", user.getProfilResmi())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY);

        return "token " + builder.compact();
    }
    }
