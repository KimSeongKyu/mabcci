package com.mabcci.domain.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Phone {

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

}
