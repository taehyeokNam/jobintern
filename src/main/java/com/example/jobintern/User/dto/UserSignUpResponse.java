package com.example.jobintern.User.dto;

import com.example.jobintern.User.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserSignUpResponse {

    private final String username;
    private final String nickname;
    private List<AuthorityResponse> authorities;

    public UserSignUpResponse(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.authorities = List.of(new AuthorityResponse(user.getRole().name()));
    }

    @Getter
    public static class AuthorityResponse {
        private String authorityName;

        public AuthorityResponse(String authorityName) {
            this.authorityName = authorityName;
        }
    }
}
