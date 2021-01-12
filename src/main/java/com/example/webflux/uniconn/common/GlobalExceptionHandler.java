package com.example.webflux.uniconn.common;

import com.example.webflux.uniconn.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        logger.error("exception occur", ex);

        ServerHttpResponse response = exchange.getResponse();
        DataBuffer buffer = getResponseBuffer(response, ex);
        return response.writeWith(Mono.just(buffer));
    }

    private DataBuffer getResponseBuffer(ServerHttpResponse response, Throwable ex) {
        if (ex instanceof ExceptionResponse) {
            ExceptionResponse exception = (ExceptionResponse) ex;
            return getResponseBuffer(response, exception.getHttpStatus(), exception.getErrorMessage());
        }
        return getResponseBuffer(response, HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 에러가 발생했습니다");
    }

    private DataBuffer getResponseBuffer(ServerHttpResponse response, HttpStatus httpStatus, String message) {
        response.setStatusCode(httpStatus);
        return response.bufferFactory().wrap(message.getBytes());
    }
}

