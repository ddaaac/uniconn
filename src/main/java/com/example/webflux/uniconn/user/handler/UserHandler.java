package com.example.webflux.uniconn.user.handler;

import com.example.webflux.uniconn.user.event.UserCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class UserHandler {

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        Mono<UserCreateCommand> command = serverRequest.bodyToMono(UserCreateCommand.class);
        return ServerResponse.created(URI.create("id")).build();
    }
}
