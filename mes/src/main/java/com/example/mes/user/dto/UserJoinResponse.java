package com.example.mes.user.dto;

import com.example.mes.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinResponse {

    private Integer userId;

    private String email;

    @Builder
    public UserJoinResponse(Integer userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public static UserJoinResponse toResponse(User user) {
        return UserJoinResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .build();
    }
}
