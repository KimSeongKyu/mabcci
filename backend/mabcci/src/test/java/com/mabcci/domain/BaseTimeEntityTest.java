package com.mabcci.domain;

import com.mabcci.domain.BaseTimeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

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

    @DisplayName("BaseTimeEntity 인스턴스 getter 기능 테스트")
    @Test
    void getter_test() {
        final LocalDateTime now = LocalDateTime.now();
        final BaseTimeEntity baseTimeEntity = new BaseTimeEntity();
        ReflectionTestUtils.setField(baseTimeEntity, "createdDate", now);
        ReflectionTestUtils.setField(baseTimeEntity, "modifiedDate", now);

        assertAll(
                () -> assertThat(baseTimeEntity.createdDate()).isEqualTo(now),
                () -> assertThat(baseTimeEntity.modifiedDate()).isEqualTo(now)
        );
    }
}