package com.mabcci.domain.ootd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdRegisterRequestTest {

    private OotdRegisterRequest ootdRegisterRequest;

    @BeforeEach
    void setUp() {
        ootdRegisterRequest = new OotdRegisterRequest("닉네임", "내용", "상의", "하의", "신발", "악세사리");
    }

    @DisplayName("OotdRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdRegisterRequest).isNotNull(),
                () -> assertThat(ootdRegisterRequest).isExactlyInstanceOf(OotdRegisterRequest.class)
        );
    }
}
