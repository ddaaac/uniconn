package com.example.webflux.uniconn.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll().block();
    }

    @Test
    void findByEmail() {
        Mono<User> user = userRepository.save(User.builder().email("bjkim@test.com").build());

        Mono<User> result = user.flatMap(u -> userRepository.findByEmail(u.getEmail()));

        StepVerifier.create(result)
                .consumeNextWith(saved -> assertAll(
                        () -> assertThat(saved.getId()).isNotNull(),
                        () -> assertThat(saved.getEmail()).isEqualTo("bjkim@test.com")
                )).verifyComplete();
    }

    @Test
    void findByEmailNotExist() {
        Mono<User> result = userRepository.findByEmail("not-exist-email");

        StepVerifier.create(result)
                .verifyComplete();
    }

    // TODO: unique 잘먹여서 통과하게 만들어보자
    @Disabled
    @Test
    void duplicateEmailThrowException() {
        User user = userRepository.save(User.builder().email("bjkim@test.com").build()).block();

        Mono<User> duplicateSaved = userRepository.save(User.builder().email(user.getEmail()).build());

        StepVerifier.create(duplicateSaved)
                .expectError(IllegalArgumentException.class)
                .verify();
    }
}
