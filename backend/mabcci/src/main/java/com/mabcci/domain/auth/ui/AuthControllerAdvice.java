package com.mabcci.domain.auth.ui;

import com.mabcci.domain.auth.exception.NotLoginMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(NotLoginMemberException.class)
    public ResponseEntity handleNotLoginMemberException(final NotLoginMemberException notLoginMemberException) {
        return ResponseEntity.badRequest()
                .body(notLoginMemberException.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNotLoginNullPointerException(final NullPointerException nullPointerException) {
        return ResponseEntity.badRequest()
                .body(nullPointerException.toString());
    }
}
