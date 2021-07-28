package com.mabcci.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PhoneTest {

    public static final Phone PHONE = Phone.of("010-1234-5678");

    @DisplayName("Phone 인스턴스 생성 여부 테스트")
    @Test
    void constructor() {
        final String phoneString = "010-1234-5678";
        final Phone phone = Phone.of(phoneString);

        assertAll(
                () -> assertThat(phone).isNotNull(),
                () -> assertThat(phone).isExactlyInstanceOf(Phone.class)
        );
    }

    @DisplayName("Phone 인스턴스 phone() 기능 테스트")
    @Test
    void phone_test() {
        final String phoneString = "010-1234-5678";
        final Phone phone = Phone.of(phoneString);
        assertThat(phone.phone()).isEqualTo(phoneString);
    }

}