package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.exception.MemberNotFoundException;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity handleMemberNotFoundException(final MemberNotFoundException memberNotFoundException) {
        return ResponseEntity.badRequest()
                .body(memberNotFoundException.toString());
    }

    @ExceptionHandler(BeanDefinitionValidationException.class)
    public ResponseEntity handleBeanDefinitionValidationException(final BeanDefinitionValidationException beanDefinitionValidationException) {
        return ResponseEntity.badRequest()
                .body(beanDefinitionValidationException.toString());
    }
}

