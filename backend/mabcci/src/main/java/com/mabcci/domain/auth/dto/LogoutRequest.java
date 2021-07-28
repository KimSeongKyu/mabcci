package com.mabcci.domain.auth.dto;

import com.mabcci.domain.model.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class LogoutRequest {

    @Valid
    @NotNull
    private Email email;

    private LogoutRequest() { }

    public LogoutRequest(@Valid final Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
