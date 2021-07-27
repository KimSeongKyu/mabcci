package com.mabcci.domain.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public final class LoginRequest {

    @NotBlank
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

    public final String getEmail() {
        return email;
    }

    public final String getPassword() {
        return password;
    }
}
