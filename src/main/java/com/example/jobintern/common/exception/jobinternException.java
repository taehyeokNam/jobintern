package com.example.jobintern.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class jobinternException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorCode errorCode;

    public jobinternException(ErrorCode errorCode) {
        super(errorCode.getHttpStatus() + " " + errorCode.getMessage());
        this.status = errorCode.getHttpStatus();
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
