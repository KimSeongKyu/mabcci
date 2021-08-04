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

    @DisplayName("OotdRegisterRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdRegisterRequest.getNickname()).isEqualTo("닉네임"),
                () -> assertThat(ootdRegisterRequest.getContent()).isEqualTo("내용"),
                () -> assertThat(ootdRegisterRequest.getTop()).isEqualTo("상의"),
                () -> assertThat(ootdRegisterRequest.getBottom()).isEqualTo("하의"),
                () -> assertThat(ootdRegisterRequest.getShoes()).isEqualTo("신발"),
                () -> assertThat(ootdRegisterRequest.getAccessory()).isEqualTo("악세사리")
        );
    }
}
