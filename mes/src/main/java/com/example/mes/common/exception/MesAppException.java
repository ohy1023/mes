package com.example.mes.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MesAppException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

}