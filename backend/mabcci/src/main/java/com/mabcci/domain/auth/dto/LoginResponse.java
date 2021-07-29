package com.mabcci.domain.auth.dto;

import com.mabcci.domain.auth.domain.vo.JwtToken;

public final class LoginResponse {

    private JwtToken accessToken;
    private JwtToken refreshToken;

    private LoginResponse() {
    }

    public LoginResponse(final JwtToken accessToken, final JwtToken refreshToken) {
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
