package com.mabcci.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Embeddable
public class Password {

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    public static Password of(final String password) {
        return new Password(password);
    }

    protected Password() {
    }

    private Password(final String password) {
        this.password = password;
    }

    public boolean checkPassword(final Password other) {
        return this.equals(other);
    }

    @JsonValue
    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password(), password1.password());
    }

    @Override
    public int hashCode() {
        return Objects.hash(password());
    }

}
