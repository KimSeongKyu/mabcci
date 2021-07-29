package com.mabcci.domain.auth.domain;

import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.model.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class RefreshToken {

    @NotNull
    @EmbeddedId
    @AttributeOverride(name = "email", column =
    @Column(name = "refresh_token_email", nullable = false, unique = true))
    private Email email;

    @NotNull
    @Embedded
    @AttributeOverride(name = "jwt_token", column =
    @Column(name = "refresh_token", nullable = false, unique = true))
    private JwtToken refreshToken;

    protected RefreshToken() {
    }

    protected RefreshToken(final RefreshTokenBuilder refreshTokenBuilder) {
        this.email = refreshTokenBuilder.email;
        this.refreshToken = refreshTokenBuilder.refreshToken;
    }

    public static RefreshTokenBuilder builder() {
        return new RefreshTokenBuilder();
    }

    public Email email() {
        return email;
    }

    public JwtToken refreshToken() {
        return refreshToken;
    }

    public static class RefreshTokenBuilder {
        private Email email;
        private JwtToken refreshToken;

        private RefreshTokenBuilder() {
        }

        public RefreshTokenBuilder email(final String email) {
            return email(Email.of(email));
        }

        public RefreshTokenBuilder email(final Email email) {
            this.email = email;
            return this;
        }

        public RefreshTokenBuilder refreshToken(final String refreshToken) {
            return refreshToken(JwtToken.of(refreshToken));
        }

        public RefreshTokenBuilder refreshToken(final JwtToken refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public RefreshToken build() {
            return new RefreshToken(this);
        }
    }
}
