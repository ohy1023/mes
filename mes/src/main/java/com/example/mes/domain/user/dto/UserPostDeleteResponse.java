package com.example.mes.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPostDeleteResponse {

    private String message;
    private Long postId;

}
