package com.example.jobintern;

import com.example.jobintern.User.dto.UserSignInRequest;
import com.example.jobintern.User.dto.UserSignInResponse;
import com.example.jobintern.User.dto.UserSignUpRequest;
import com.example.jobintern.User.dto.UserSignUpResponse;
import com.example.jobintern.User.entity.User;
import com.example.jobintern.User.enums.UserRole;
import com.example.jobintern.User.repository.UserRepository;
import com.example.jobintern.User.service.UserService;
import com.example.jobintern.common.config.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signup_success() {
        // Given
        UserSignUpRequest request = new UserSignUpRequest("testname", "password", "nickname");
        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.empty());

        // When
        UserSignUpResponse response = userService.singup(request);

        // Then
        assertEquals("testname", response.getUsername());
        assertEquals("nickname", response.getNickname());
        assertEquals(UserRole.ROLE_USER, UserRole.of(response.getAuthorities().get(0).getAuthorityName()));
    }

    @Nested
    class SignTests {

        @BeforeEach
        void setUpSignTest() {

            UserSignUpRequest request = new UserSignUpRequest("testname", "password", "nickname");
            User mockUser = new User(request, "encodedPassword");

            when(userRepository.findByUsernameOrThrow("username")).thenReturn(mockUser);

            when(passwordEncoder.matches("password!", "encodedPassword")).thenReturn(true);

            when(jwtUtil.createToken(mockUser.getId(), mockUser.getUsername(), mockUser.getRole()))
                    .thenReturn("jwtToken");
        }

        @Test
        void sign_success() {
            // Given
            UserSignInRequest request = new UserSignInRequest("testname", "password!");

            // When
            UserSignInResponse response = userService.sign(request);

            // Then
            assertEquals("jwtToken", response.getToken());
        }
    }
}
