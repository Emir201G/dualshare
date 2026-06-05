package com.app.dualshare.exceptions;

public class FirebaseAuthenticationException extends RuntimeException {
    public FirebaseAuthenticationException(String firebaseUid) {
        super("Firebase authentication failed or user record missing for UID: " + firebaseUid);
    }
}