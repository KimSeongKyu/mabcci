package com.mabcci.domain.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class LoginRequest {

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;

    private LoginRequest() {
    }

    public LoginRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

}
