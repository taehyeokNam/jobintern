package com.example.jobintern.User.entity;

import com.example.jobintern.User.dto.UserSignUpRequest;
import com.example.jobintern.User.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(UserSignUpRequest userSignUpRequest, String password) {
        this.username = userSignUpRequest.getUsername();
        this.password = password;
        this.nickname = userSignUpRequest.getNickname();
        this.role = UserRole.ROLE_USER;
    }
}
