package com.example.mes.domain.user.dto;

import com.example.mes.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequest {

    private String userName;

    private String email;
    private String password;


    public User toEntity(String password) {
        return User.builder()
                .userName(this.userName)
                .email(this.email)
                .password(password)
                .build();
    }

    @Builder
    public UserJoinRequest(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
