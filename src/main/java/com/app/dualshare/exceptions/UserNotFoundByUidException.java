package com.app.dualshare.exceptions;

public class UserNotFoundByUidException extends RuntimeException {
    public UserNotFoundByUidException(String firebaseUid) {
        super("User not found, with id: " + firebaseUid);
    }
}
