package com.app.dualshare.exceptions;

public class AlreadyFriendsException extends RuntimeException {
    public AlreadyFriendsException(String username) {
        super("You're already friends with " + username);
    }
}
