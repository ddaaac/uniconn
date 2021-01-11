package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
//        HttpServer.create().host("localhost").port(8080).handle(
//                new ReactorHttpHandlerAdapter(
//                        toHttpHandler(
//                                route(
//                                        path("/hello"),
//                                        req -> ok().body(fromValue("Hello Funtional"))
//                                )
//                        )
//                )
//        ).bindNow().onDispose().block();
    }
}
