package com.mabcci.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Phone {

    @NotEmpty
    private String phone;

    public static Phone of(final String phone) {
        return new Phone(phone);
    }

    protected Phone() {
    }

    private Phone(final String phone) {
        this.phone = phone;
    }

    @JsonValue
    public String phone() {
        return phone;
    }
}
