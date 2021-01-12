package com.example.webflux.uniconn.user.domain;

import com.example.webflux.uniconn.user.event.UserCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> create(Mono<UserCreateCommand> command) {
        return command
                .flatMap(c -> checkDuplicate(c.getEmail()))
                .then(command)
                .flatMap(c -> userRepository.save(c.toEntity()));
    }

    private Mono<User> checkDuplicate(String email) {
        return userRepository.findByEmail(email)
                .flatMap(user -> Mono.error(new IllegalArgumentException("이미 존재하는 이메일입니다.")));
    }
}
