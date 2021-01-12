package com.example.webflux.uniconn.user.domain;

import com.example.webflux.uniconn.user.event.UserCreateCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll().block();
    }

    @Test
    void create() {
        UserCreateCommand userCreateCommand = com.example.webflux.uniconn.user.event.UserCreateCommand.builder()
                .name("김범준")
                .password("password")
                .email("bjkim@korea.ac.kr")
                .univ(Univ.KOREA)
                .major(Major.COMPUTER)
                .build();

        Mono<User> result = userService.create(Mono.just(userCreateCommand));

        StepVerifier.create(result)
                .consumeNextWith(user -> assertAll(
                        () -> assertThat(user.getId()).isNotNull(),
                        () -> assertThat(user.isVerified()).isFalse())
                ).verifyComplete();
    }

    @Test
    void createDuplicate() {
        UserCreateCommand userCreateCommand = com.example.webflux.uniconn.user.event.UserCreateCommand.builder()
                .name("김범준")
                .password("password")
                .email("bjkim@korea.ac.kr")
                .univ(Univ.KOREA)
                .major(Major.COMPUTER)
                .build();
        userRepository.save(userCreateCommand.toEntity()).block();

        Mono<User> result = userService.create(Mono.just(userCreateCommand));

        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .log()
                .verify();
    }
}
