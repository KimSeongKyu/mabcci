package com.mabcci.domain.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Password;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class LoginRequest {

    @Valid @NotNull @JsonProperty("email")
    private Email email;

    @Valid @NotNull @JsonProperty("password")
    private Password password;

    private LoginRequest() {
    }

    public LoginRequest(final Email email, final Password password) {
        this.email = email;
        this.password = password;
    }

    public final Email email() {
        return email;
    }

    public final Password password() {
        return password;
    }
}
