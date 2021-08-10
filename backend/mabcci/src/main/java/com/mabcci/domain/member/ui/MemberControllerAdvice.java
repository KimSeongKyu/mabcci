package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.exception.MemberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;


@RestControllerAdvice
public class MemberControllerAdvice {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity handleMemberNotFoundException(final MemberNotFoundException memberNotFoundException) {
        log.error("notFound Member Exception : {}", memberNotFoundException.getMessage(), memberNotFoundException);
        return ResponseEntity.badRequest().body(memberNotFoundException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(final Exception exception) {
        log.error("Internal Error : {}", exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(Optional.empty());
    }
}

