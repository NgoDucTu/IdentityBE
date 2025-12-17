package com.tund.identity.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    DEFAULT(999, "Default exception error", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILKEY(900, "Fail enum key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(101, "User existed", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(102, "Username must be at least 3 charactors", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(103, "Password must be at least 8 charactors", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(104, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(105, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    ACCESS_DENY(106, "Access deny", HttpStatus.FORBIDDEN);

    private int code;
    private String message;
    HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
