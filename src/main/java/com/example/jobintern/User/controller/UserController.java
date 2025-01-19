package com.example.jobintern.User.controller;

import com.example.jobintern.User.dto.UserSignInRequest;
import com.example.jobintern.User.dto.UserSignUpRequest;
import com.example.jobintern.User.service.UserService;
import com.example.jobintern.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "예제 API", description = "Swagger 테스트용 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입 API")
    @Parameters({
            @Parameter(name = "username", description = "이름", example = "mouse"),
            @Parameter(name = "password", description = "비밀번호", example = "123123"),
            @Parameter(name = "nickname", description = "닉네임", example = "jerry"),
    })
    @PostMapping("/users/signup")
    public ResponseEntity<ApiResponse<?>> signup(@RequestBody UserSignUpRequest userSignUpRequest) {
        return ResponseEntity.ok(ApiResponse.success(userService.signup(userSignUpRequest)));
    }

    @Operation(summary = "로그인", description = "로그인 API")
    @Parameters({
            @Parameter(name = "username", description = "이름", example = "mouse"),
            @Parameter(name = "password", description = "비밀번호", example = "123123"),
    })
    @PostMapping("/users/signin")
    public ResponseEntity<ApiResponse<?>> sign(@RequestBody UserSignInRequest userSignInRequest) {
        return ResponseEntity.ok(ApiResponse.success(userService.sign(userSignInRequest)));
    }
}
