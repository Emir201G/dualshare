package com.app.dualshare.exceptions;

public class UserNotFoundByShareCodeException extends RuntimeException {
    public UserNotFoundByShareCodeException(String code) {
        super("User not found by share code: " + code);
    }
}
