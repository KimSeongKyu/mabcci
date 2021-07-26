package com.mabcci.domain.auth.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    private String email;

    @NotBlank
    private String refreshToken;

    @Builder
    public RefreshToken(final String email, final String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
