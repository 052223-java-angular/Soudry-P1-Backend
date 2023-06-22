package com.revature.DDWar.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.revature.DDWar.utils.custom_exceptions.InvalidTokenException;
import com.revature.DDWar.utils.custom_exceptions.ResourceConflictException;
import com.revature.DDWar.utils.custom_exceptions.UserNotFoundException;

import java.util.Map;
import java.util.Date;

@RestControllerAdvice
public class ExceptionController {
        @ExceptionHandler(ResourceConflictException.class)
        public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidTokenException(InvalidTokenException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }
    
}
