package com.mabcci.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Email {

    @javax.validation.constraints.Email
    @NotEmpty
    @Column(name = "email")
    private String email;

    public static Email of(final String email) {
        return new Email(email);
    }

    protected Email() {
    }

    private Email(final String email) {
        this.email = email;
    }

    public String email() {
        return this.email;
    }
}
