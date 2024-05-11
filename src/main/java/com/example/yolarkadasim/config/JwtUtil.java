package com.example.yolarkadasim.config;

import com.example.yolarkadasim.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static String generateToken(String eposta, String sifre) {
        return Jwts.builder()
                .claim("eposta", eposta)
                .claim("sifre", sifre)
                .signWith(SECRET_KEY)
                .compact();
    }
}
