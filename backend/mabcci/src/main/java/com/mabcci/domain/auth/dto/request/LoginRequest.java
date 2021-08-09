package com.mabcci.domain.auth.dto.request;

import com.mabcci.global.common.Email;
import com.mabcci.global.common.Password;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class LoginRequest {

    @Valid
    @NotNull
    private Email email;

    @Valid
    @NotNull
    private Password password;

    private LoginRequest() {
    }

    public LoginRequest(@Valid final Email email, @Valid final Password password) {
        this.email = email;
        this.password = password;
    }

    public final Email getEmail() {
        return email;
    }

    public final Password getPassword() {
        return password;
    }
}
