package com.example.jobintern.User.repository;

import com.example.jobintern.User.entity.User;
import com.example.jobintern.common.exception.ErrorCode;
import com.example.jobintern.common.exception.jobinternException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    default User findByUsernameOrThrow(String username) {
        return findByUsername(username).orElseThrow(() -> new jobinternException(ErrorCode.USER_SIGNIN_ERROR));
    }
}
