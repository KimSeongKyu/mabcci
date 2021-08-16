package com.mabcci.domain.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class LogoutRequest {

    @Valid @NotNull @JsonProperty("email")
    private Email email;

    private LogoutRequest() {
    }

    public LogoutRequest(final Email email) {
        this.email = email;
    }

    public Email email() {
        return email;
    }
}
