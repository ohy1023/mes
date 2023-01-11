package com.example.mes.exception;

import com.example.mes.domain.response.ErrorResponse;
import com.example.mes.domain.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(MesAppException.class)
    public ResponseEntity<?> appExceptionHandler(MesAppException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error("ERROR", errorResponse));
    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> sqlExceptionHandler(SQLException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.DATABASE_ERROR, e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(Response.error("ERROR", errorResponse));
    }


//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
//        return ResponseEntity.status(CONFLICT)
//                .body(e.getMessage());
//    }
}
