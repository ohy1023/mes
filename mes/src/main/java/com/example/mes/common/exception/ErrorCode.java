package com.example.mes.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 ITEM을 찾을 수 없습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB에러"),
    ;

    private HttpStatus status;
    private String message;
}