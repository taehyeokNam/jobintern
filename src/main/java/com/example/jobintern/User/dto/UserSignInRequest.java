package com.example.jobintern.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInRequest {

    private String username;
    private String password;
}
