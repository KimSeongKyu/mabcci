package com.mabcci.domain.picture.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PictureTypeTest {

    @DisplayName("PictureType 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        Arrays.stream(PictureType.values())
                .forEach(pictureType -> assertAll(
                        () -> assertThat(pictureType).isNotNull(),
                        () -> assertThat(pictureType).isExactlyInstanceOf(PictureType.class))
                );
    }

    @DisplayName("PictureType 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(PictureType.OOTD.type()).isEqualTo("ootd"),
                () -> assertThat(PictureType.PROFILE.type()).isEqualTo("profile"),
                () -> assertThat(PictureType.BOARD.type()).isEqualTo("board")
        );
    }
}
