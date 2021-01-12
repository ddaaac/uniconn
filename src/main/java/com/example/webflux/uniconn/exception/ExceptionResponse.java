package com.example.webflux.uniconn.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class ExceptionResponse extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
