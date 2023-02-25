package com.example.mes.user.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN("ROLE_AMDIN"), USER("ROLE_USER");

    private final String key;
}
