package com.mabcci.auth.controller;

import com.mabcci.auth.exception.NotLoginMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(NotLoginMemberException.class)
    public ResponseEntity handleNotLoginMemberException(final NotLoginMemberException notLoginMemberException) {
        return ResponseEntity.badRequest().body(notLoginMemberException.toString());
    }
}
