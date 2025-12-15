package com.tund.identity.service.exception;

public enum ErrorCode {
    DEFAULT(999, "Default exception error"),
    FAILKEY(900, "Fail enum key"),
    USER_EXISTED(101, "User existed"),
    INVALID_USERNAME(102, "Username must be at least 3 charactors"),
    INVALID_PASSWORD(103, "Password must be at least 8 charactors"),
    USER_NOT_FOUND(104, "User not found"),
    UNAUTHENTICATED(105, "Unauthenticated");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
