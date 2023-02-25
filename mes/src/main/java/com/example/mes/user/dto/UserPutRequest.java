package com.example.mes.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UserPutRequest {
    private String userName;
    private MultipartFile image;

    @Builder
    public UserPutRequest(String userName, MultipartFile image) {
        this.userName = userName;
        this.image = image;
    }
}
