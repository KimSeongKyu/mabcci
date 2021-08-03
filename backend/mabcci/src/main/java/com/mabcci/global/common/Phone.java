package com.mabcci.global.common;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return Objects.equals(phone(), phone1.phone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone());
    }
}
