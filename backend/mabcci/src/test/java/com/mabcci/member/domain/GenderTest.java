package com.mabcci.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @DisplayName("Gender 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(Gender.MALE).isExactlyInstanceOf(Gender.class),
                () -> assertThat(Gender.FEMALE).isExactlyInstanceOf(Gender.class)
        );
    }
}