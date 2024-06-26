package com.example.yolarkadasim.config;

import com.example.yolarkadasim.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // Token geçerlilik süresi (örneğin, 1 gün)


    public static String generateToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .claim("kullaniciId",user.getKullaniciId())
                .claim("ad", user.getAd())
                .claim("soyad", user.getSoyad())
                .claim("eposta", user.getEposta())
                .claim("kayitTarihi",user.getKayitTarihi() != null ? user.getKayitTarihi()  .toString() : null)
                .claim("profilResmi", user.getProfilResmi())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY);

        return  "token " + builder.compact();
    }
    }
