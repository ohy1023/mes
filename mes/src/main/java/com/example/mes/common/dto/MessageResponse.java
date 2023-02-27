package com.example.mes.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageResponse {
    private Long id;

    private String message;

    @Builder
    public MessageResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
