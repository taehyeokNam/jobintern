package com.example.jobintern.User.controller;

import com.example.jobintern.User.dto.UserSignInRequest;
import com.example.jobintern.User.dto.UserSignUpRequest;
import com.example.jobintern.User.service.UserService;
import com.example.jobintern.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<ApiResponse<?>> singup(@RequestBody UserSignUpRequest userSignUpRequest) {
        return ResponseEntity.ok(ApiResponse.success(userService.singup(userSignUpRequest)));
    }

    @PostMapping("/users/signin")
    public ResponseEntity<ApiResponse<?>> sign(@RequestBody UserSignInRequest userSignInRequest) {
        return ResponseEntity.ok(ApiResponse.success(userService.sign(userSignInRequest)));
    }
}
