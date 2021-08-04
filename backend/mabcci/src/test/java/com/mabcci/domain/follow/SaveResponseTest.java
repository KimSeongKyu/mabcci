package com.mabcci.domain.follow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SaveResponseTest {

    @DisplayName("SaveResponse 인스턴스 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final SaveResponse saveResponse = new SaveResponse();

        assertAll(
                () -> assertThat(saveResponse).isNotNull(),
                () -> assertThat(saveResponse).isExactlyInstanceOf(SaveResponse.class)
        );
    }
}