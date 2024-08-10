package com.example.mes.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdatePasswordRequest {
    private String password;
    private String newPassword;
    private String reNewPassword;

    @Builder
    public UserUpdatePasswordRequest(String password, String newPassword, String reNewPassword) {
        this.password = password;
        this.newPassword = newPassword;
        this.reNewPassword = reNewPassword;
    }
}
