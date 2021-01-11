package com.example.webflux.uniconn.user.router;

import com.example.webflux.uniconn.user.domain.Major;
import com.example.webflux.uniconn.user.domain.Univ;
import com.example.webflux.uniconn.user.event.UserCreateCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class UserRoutersTest {

    @Autowired
    private UserRouters userRouters;
    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient
                .bindToRouterFunction(userRouters.routeUser())
                .build();
    }

    @DisplayName("UserCreateCommand가 주어졌을 때, user로 post 요청을 보내면 User가 생성되고 id가 반환된다")
    @Test
    void create() {
        UserCreateCommand userCreateCommand = UserCreateCommand.builder()
                .name("김범준")
                .password("password")
                .email("bjkim@korea.ac.kr")
                .univ(Univ.KOREA)
                .major(Major.COMPUTER)
                .build();

        client.post()
                .uri("/user")
                .bodyValue(userCreateCommand)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location");
    }
}