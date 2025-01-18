package com.example.jobintern.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    JWT_UNSAVABLE(HttpStatus.BAD_REQUEST, "JWT 토큰을 쿠키에 저장하는데 실패 했습니다."),
    JWT_INVALID(HttpStatus.BAD_REQUEST, "유효하지 않는 JWT 토큰 입니다"),
    JWT_EXPIRED(HttpStatus.BAD_REQUEST, "만료된 토큰 입니다."),
    JWT_TYPE_ERROR(HttpStatus.BAD_REQUEST, "지원되지 않는 JWT 토큰 입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    USER_ID_DUPLICATION(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다."),
    TOKEN_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "토큰을 찾지 못햇습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
