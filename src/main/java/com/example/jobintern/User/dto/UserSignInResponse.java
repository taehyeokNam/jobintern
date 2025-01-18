package com.example.jobintern.User.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSignInResponse {

    private final String token;
}
