package com.mabcci.domain.ootdpicture.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdPictureTest {

    private OotdPicture ootdPicture;

    @BeforeEach
    void setUp() {
        ootdPicture = OotdPicture.builder()
                .ootd(OOTD)
                .url("testUrl")
                .fileName("testFileName")
                .build();
    }

    @DisplayName("OotdPicture 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdPicture).isNotNull(),
                () -> assertThat(ootdPicture).isExactlyInstanceOf(OotdPicture.class)
        );
    }
}
