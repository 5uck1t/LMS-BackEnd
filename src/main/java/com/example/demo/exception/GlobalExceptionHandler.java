package com.example.demo.exception;


import com.example.demo.model.ErrorLog;
import com.example.demo.repository.ErrorRepository;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ErrorRepository errorRepository;


    private void logError(Exception ex) {
        ErrorLog error = new ErrorLog();

        error.setExceptionType(ex.getClass().getSimpleName());
        error.setPoruka(ex.getMessage());
        error.setVreme(LocalDateTime.now());
        error.setObrisano(false);

        errorRepository.save(error);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        logError(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        logError(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request: " + ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        System.out.println("Unauthorized: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + ex.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleASecurityException(SignatureException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        System.out.println("Unauthorized: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFound(Exception ex) {
        logError(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
