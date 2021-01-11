package com.example.webflux.uniconn.user.event;

import com.example.webflux.uniconn.user.domain.Major;
import com.example.webflux.uniconn.user.domain.Univ;
import com.example.webflux.uniconn.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserCreateCommand {

    private String name;
    private String email;
    private String password;
    private Univ univ;
    private Major major;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .univ(univ)
                .major(major)
                .build();
    }
}
