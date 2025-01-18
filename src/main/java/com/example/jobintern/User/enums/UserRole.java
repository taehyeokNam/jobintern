package com.example.jobintern.User.enums;

import com.example.jobintern.common.exception.jobinternException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ROLE_USER(Authority.USER);

    private final String role;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("존재하지 않는 권한입니다."));
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
    }
}

