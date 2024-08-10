package com.example.mes.domain.user.dto;

import com.example.mes.domain.user.User;
import com.example.mes.domain.user.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRoleResponse {
    private UserRole userRole;

    @Builder
    public UserRoleResponse(UserRole userRole) {
        this.userRole = userRole;
    }

    public static UserRoleResponse toResponse(User user) {
        return UserRoleResponse.builder()
                .userRole(user.getUserRole())
                .build();
    }
}
