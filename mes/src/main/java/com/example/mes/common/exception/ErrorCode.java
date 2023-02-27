package com.example.mes.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 ITEM을 찾을 수 없습니다."),

    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 이메일을 찾을 수 없습니다."),

    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 거래처를 찾을 수 없습니다."),

    DUPLICATED_EMAIL(HttpStatus.CONFLICT,"이미 존재하는 이메일 입니다."),

    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED,"해당 권한이 없습니다."),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다."),

    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB에러"),
    ;

    private HttpStatus status;
    private String message;
}