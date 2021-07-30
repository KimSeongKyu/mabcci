package com.mabcci.domain.auth.dto;

import com.mabcci.domain.auth.domain.vo.JwtToken;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class LoginResponse {

    @Valid
    @NotNull
    private JwtToken accessToken;

    @Valid
    @NotNull
    private JwtToken refreshToken;

    private LoginResponse() {
    }

    public LoginResponse(@Valid final JwtToken accessToken, @Valid final JwtToken refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public final JwtToken getAccessToken() {
        return accessToken;
    }

    public final JwtToken getRefreshToken() {
        return refreshToken;
    }
}
