package com.example.webflux.uniconn.user.router;

import com.example.webflux.uniconn.user.domain.Major;
import com.example.webflux.uniconn.user.domain.Univ;
import com.example.webflux.uniconn.user.domain.UserRepository;
import com.example.webflux.uniconn.user.event.UserCreateCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "100000000")
class UserRoutersTest {

    @Autowired
    private UserRouters userRouters;

    @Autowired
    private UserRepository userRepository;
    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient
                .bindToRouterFunction(userRouters.routeUser())
                .build();

        userRepository.deleteAll().block();
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

    @DisplayName("User 생성 시, 중복된 이메일이 이미 존재할 때, 400 응답을 받는다.")
    @Test
    void createDuplicateEmail() {
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
                .expectStatus().isCreated();
        client.post()
                .uri("/user")
                .bodyValue(userCreateCommand)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .consumeWith(it -> assertThat(it.getResponseBody()).isEqualTo("이미 존재하는 이메일입니다.".getBytes()));
    }
}
