package com.mabcci.domain.auth.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    @Column(name = "REFRESH_TOKEN_EMAIL")
    private String email;

    @NotBlank
    @Column(name = "REFRESH_TOKEN", nullable = false, unique = true)
    private String refreshToken;

    @Builder
    public RefreshToken(final String email, final String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
