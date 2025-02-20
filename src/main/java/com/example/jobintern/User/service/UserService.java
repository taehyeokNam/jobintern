package com.example.jobintern.User.service;

import com.example.jobintern.User.dto.UserSignInRequest;
import com.example.jobintern.User.dto.UserSignInResponse;
import com.example.jobintern.User.dto.UserSignUpRequest;
import com.example.jobintern.User.dto.UserSignUpResponse;
import com.example.jobintern.User.entity.User;
import com.example.jobintern.User.repository.UserRepository;
import com.example.jobintern.common.config.JwtUtil;
import com.example.jobintern.common.exception.ErrorCode;
import com.example.jobintern.common.exception.jobinternException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
    public UserSignUpResponse signup(UserSignUpRequest userSignUpRequest) {

        Optional<User> existingUser = userRepository.findByUsername(userSignUpRequest.getUsername());

        if (existingUser.isPresent()) {
            throw new jobinternException(ErrorCode.USER_ID_DUPLICATION);
        }

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(userSignUpRequest.getPassword());
        User user = new User(userSignUpRequest, encodePassword);
        userRepository.save(user);

        return new UserSignUpResponse(user);
    }

    @Transactional
    public UserSignInResponse sign(UserSignInRequest userSignInRequest) {

        User user = userRepository.findByUsernameOrThrow(userSignInRequest.getUsername());

        if (!passwordEncoder.matches(userSignInRequest.getPassword(), user.getPassword())) {
            throw new jobinternException(ErrorCode.USER_SIGNIN_ERROR);
        }

        String token = jwtUtil.createToken(user.getId(), user.getUsername(), user.getRole());

        return new UserSignInResponse(token);
    }
}
