package com.mabcci.global.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BaseTimeEntityTest {


    @DisplayName("BaseTimeEntity 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

        assertAll(
                () -> assertThat(baseTimeEntity).isNotNull(),
                () -> assertThat(baseTimeEntity).isExactlyInstanceOf(BaseTimeEntity.class)
        );
    }
}