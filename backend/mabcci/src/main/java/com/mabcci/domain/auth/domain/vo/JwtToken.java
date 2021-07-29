package com.mabcci.domain.auth.domain.vo;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JwtToken implements Serializable {

    @NotBlank
    @Column(name = "jwt_token", length = 500, nullable = false, unique = true)
    private String jwtToken;

    protected JwtToken() {
    }

    private JwtToken(final String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public static JwtToken of(final String jwtToken) {
        return new JwtToken(jwtToken);
    }

    @JsonValue
    public String jwtToken() {
        return jwtToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtToken jwtToken1 = (JwtToken) o;
        return Objects.equals(jwtToken, jwtToken1.jwtToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwtToken);
    }
}
