package com.example.mes.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPasswordRequest {
    private String password;

    public UserPasswordRequest(String password) {
        this.password = password;
    }
}
