package com.example.yolarkadasim.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter//Request body oluşan yerde
public class UserPasswordUpdateRequest {
    private String newPassword;
}
