package com.example.webflux.uniconn.user.router.dto;

import com.example.webflux.uniconn.user.domain.Major;
import com.example.webflux.uniconn.user.domain.Univ;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateCommand {

    private String name;
    private String email;
    private String password;
    private Univ univ;
    private Major major;
}
