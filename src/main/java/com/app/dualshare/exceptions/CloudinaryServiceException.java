package com.app.dualshare.exceptions;

public class CloudinaryServiceException extends RuntimeException {
    public CloudinaryServiceException(String message) {
        super("Cloudinary service exception: " + message);
    }
}
