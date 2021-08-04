package com.mabcci.domain.ootd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdRegisterRequestTest {

    private OotdRegisterRequest ootdRegisterRequest;

    @BeforeEach
    void setUp() {
        ootdRegisterRequest = new OotdRegisterRequest(
                "닉네임", "내용", "상의", "하의", "신발", "악세사리",
                PICTURE_FILES, new ArrayList<>(List.of("해시태그1", "해시태그2"))
        );
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
