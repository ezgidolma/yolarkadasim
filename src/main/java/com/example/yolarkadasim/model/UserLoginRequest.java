package com.example.yolarkadasim.model;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String eposta;
    private String sifre;
}
