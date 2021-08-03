package com.mabcci.domain.ootdpicture.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
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

    @DisplayName("OotdPicture 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdPicture ootdPicture = new OotdPicture();

        assertAll(
                () -> assertThat(ootdPicture).isNotNull(),
                () -> assertThat(ootdPicture).isExactlyInstanceOf(OotdPicture.class)
        );
    }

    @DisplayName("OotdPicture 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdPicture.id()).isEqualTo(1L),
                () -> assertThat(ootdPicture.ootd()).isEqualTo(OOTD),
                () -> assertThat(ootdPicture.url()).isEqualTo("testUrl"),
                () -> assertThat(ootdPicture.fileName()).isEqualTo("testFileName")
        );
    }
}
