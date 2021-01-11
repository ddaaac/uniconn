package com.example.webflux.uniconn.user.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouters {

    @Bean
    RouterFunction<ServerResponse> postUserRoute() {
        return route(POST("/user"),
                req -> ServerResponse.created(URI.create("id")).build());
    }
}
