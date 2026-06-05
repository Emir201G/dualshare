package com.app.dualshare.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                status.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException userNotFoundException,
            HttpServletRequest httpServletRequest
    ) {
        return buildErrorResponse(userNotFoundException, HttpStatus.NOT_FOUND, httpServletRequest);
    }

    @ExceptionHandler(FirebaseAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleFirebaseAuthenticationException(
            FirebaseAuthenticationException firebaseAuthenticationException,
            HttpServletRequest httpServletRequest
    ) {
        return buildErrorResponse(firebaseAuthenticationException, HttpStatus.UNAUTHORIZED, httpServletRequest);
    }

    @ExceptionHandler(UserNotFoundByUidException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoudByUidException(
            UserNotFoundByUidException notFoundByUidException,
            HttpServletRequest httpServletRequest
    ) {

        return buildErrorResponse(notFoundByUidException, HttpStatus.NOT_FOUND, httpServletRequest);
    }

    public ResponseEntity<ErrorResponse> handleUserNotFoundByShareCode(
            UserNotFoundByShareCodeException notFoundByShareCode,
            HttpServletRequest httpServletRequest
    ) {
        return buildErrorResponse(notFoundByShareCode, HttpStatus.NOT_FOUND, httpServletRequest);
    }
}
