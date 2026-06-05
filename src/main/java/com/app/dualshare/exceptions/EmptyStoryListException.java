package com.app.dualshare.exceptions;

public class EmptyStoryListException extends RuntimeException {
    public EmptyStoryListException(String firebaseUdi) {
        super("Empty story list exception: " + firebaseUdi);
    }
}
