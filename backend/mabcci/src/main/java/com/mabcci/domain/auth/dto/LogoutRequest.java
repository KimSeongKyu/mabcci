package com.mabcci.domain.auth.dto;

import com.mabcci.global.common.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class LogoutRequest {

    @Valid
    @NotNull
    private Email email;

    private LogoutRequest() {
    }

    public LogoutRequest(@Valid final Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
