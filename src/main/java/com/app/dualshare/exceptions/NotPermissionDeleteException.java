package com.app.dualshare.exceptions;

public class NotPermissionDeleteException extends RuntimeException {
    public NotPermissionDeleteException(String firebase) {
        super("No permission to delete story " + firebase);
    }
}
