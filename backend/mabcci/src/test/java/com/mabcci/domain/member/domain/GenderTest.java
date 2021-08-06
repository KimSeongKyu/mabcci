package com.mabcci.domain.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GenderTest {

    @DisplayName("Gender 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(Gender.MAN).isExactlyInstanceOf(Gender.class),
                () -> assertThat(Gender.WOMAN).isExactlyInstanceOf(Gender.class)
        );
    }
}