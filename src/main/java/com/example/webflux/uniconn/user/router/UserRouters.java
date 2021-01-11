package com.example.webflux.uniconn.user.router;

import com.example.webflux.uniconn.user.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@RequiredArgsConstructor
public class UserRouters {

    private final UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> routeUser() {
        return RouterFunctions.route(POST("/user"), userHandler::createUser);
    }
}
