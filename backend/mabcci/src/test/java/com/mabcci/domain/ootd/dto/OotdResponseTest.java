package com.mabcci.domain.ootd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdResponseTest {

    private OotdResponse ootdResponse;

    @BeforeEach
    void setUp() {
        ootdResponse = new OotdResponse(1L, "닉네임", "url/name.png", new ArrayList<>(List.of("해시태그1", "해시태그2")), 10L);
    }

    @DisplayName("OotdResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdResponse).isNotNull(),
                () -> assertThat(ootdResponse).isExactlyInstanceOf(OotdResponse.class)
        );
    }

    @DisplayName("OotdResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdResponse.getId()).isEqualTo(1L),
                () -> assertThat(ootdResponse.getNickname()).isEqualTo("닉네임"),
                () -> assertThat(ootdResponse.getPicture()).isEqualTo("url/name.png"),
                () -> assertThat(ootdResponse.getHashtags()).contains("해시태그1", "해시태그2"),
                () -> assertThat(ootdResponse.getLikeCount()).isEqualTo(10L)
                );
    }
}
