package com.quiz_app.quiz_app.exception;

public enum ErrorCode {USER_EXSTED(1001, "User existed"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    USERNAME_INVALID_PASSWORD(1004, "Password must be at least 8 charecters"),
    INVALID_KEY(1001, "Invalid message key"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error");
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
