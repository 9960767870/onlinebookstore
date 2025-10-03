package com.onlinebookstore.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 Handle bad credentials (wrong username/password)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentials(BadCredentialsException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        exceptionResponse.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    // 🔹 Handle access denied (forbidden)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDenied(AccessDeniedException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage("Access Denied");
        exceptionResponse.setStatus(HttpStatus.FORBIDDEN.value());
        exceptionResponse.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);

    }

    // 🔹 Handle your custom exception and Catch-all for other exceptions
    @ExceptionHandler({CustomException.class,Exception.class})
    public ResponseEntity<ExceptionResponse> handleUserNotFound(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage("Something went wrong: " + ex.getMessage());
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
