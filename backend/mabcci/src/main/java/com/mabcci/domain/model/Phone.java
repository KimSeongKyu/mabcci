package com.mabcci.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Phone {

    private static final String PHONE_FORMAT = "%s-%s-%s";

    @Min(2)
    @Max(3)
    @NotEmpty
    private String firstNumber;

    @Min(3)
    @Max(4)
    @NotEmpty
    private String secondNumber;

    @Min(3)
    @Max(4)
    @NotEmpty
    private String thirdNumber;

    public static Phone from(final String firstNumber, final String secondNumber, final String thirdNumber) {
        return new Phone(firstNumber, secondNumber, thirdNumber);
    }

    protected Phone() {
    }

    private Phone(final String firstNumber, final String secondNumber, final String thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    public String phone() {
        return String.format(PHONE_FORMAT, firstNumber, secondNumber, thirdNumber);
    }

    @JsonValue
    public String firstNumber() {
        return firstNumber;
    }

    @JsonValue
    public String secondNumber() {
        return secondNumber;
    }

    @JsonValue
    public String thirdNumber() {
        return thirdNumber;
    }
}
