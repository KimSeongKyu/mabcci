package com.mabcci.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.auth.domain.vo.JwtToken;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public final class LoginResponse {

    @Valid @NotNull @JsonProperty("accessToken")
    private JwtToken accessToken;

    @Valid @NotNull @JsonProperty("refreshToken")
    private JwtToken refreshToken;

    private LoginResponse() {
    }

    public LoginResponse(final JwtToken accessToken, final JwtToken refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public final JwtToken accessToken() {
        return accessToken;
    }

    public final JwtToken refreshToken() {
        return refreshToken;
    }
}
