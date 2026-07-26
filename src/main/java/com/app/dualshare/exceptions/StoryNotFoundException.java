package com.app.dualshare.exceptions;

public class StoryNotFoundException extends RuntimeException {
    public StoryNotFoundException(String publicId) {
        super("No story found with id: " + publicId);
    }
}
