package com.app.dualshare.exceptions;

public class PublicIdNotFoudException extends RuntimeException {
    public PublicIdNotFoudException(String publicId) {
        super("Public Id not found by share code: " + publicId);
    }
}
