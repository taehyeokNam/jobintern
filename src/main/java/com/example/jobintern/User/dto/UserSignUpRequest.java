package com.example.jobintern.User.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

    private String username;
    private String password;
    private String nickname;
}
