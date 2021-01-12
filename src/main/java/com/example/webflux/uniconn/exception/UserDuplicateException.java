package com.example.webflux.uniconn.exception;

import org.springframework.http.HttpStatus;

public class UserDuplicateException extends ExceptionResponse {

    public UserDuplicateException() {
        super(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
    }
}
