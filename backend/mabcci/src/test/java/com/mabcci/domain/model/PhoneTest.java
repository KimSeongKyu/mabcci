package com.mabcci.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @DisplayName("Phone 인스턴스 생성 여부 테스트")
    @Test
    void constructor() {
        final String firstNumber = "010";
        final String secondNumber = "1234";
        final String thirdNumber = "5678";

        final Phone phone = Phone.from(firstNumber, secondNumber, thirdNumber);

        assertAll(
                () -> assertThat(phone).isNotNull(),
                () -> assertThat(phone).isExactlyInstanceOf(Phone.class)
        );
    }
}