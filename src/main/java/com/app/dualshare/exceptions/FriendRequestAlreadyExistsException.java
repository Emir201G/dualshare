package com.app.dualshare.exceptions;

public class FriendRequestAlreadyExistsException extends RuntimeException {
    public FriendRequestAlreadyExistsException(String username) {
        super("Friend request already exists: " + username);
    }
}
