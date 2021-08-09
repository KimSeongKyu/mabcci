package com.mabcci.domain.ootd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdUpdateRequestTest {

    private OotdUpdateRequest ootdUpdateRequest;

    @BeforeEach
    void setUp() {
        ootdUpdateRequest = new OotdUpdateRequest("내용", "상의", "하의", "신발", "악세사리",
                new ArrayList<>(List.of("해시태그1", "해시태그2")));
    }

    @DisplayName("OotdUpdateRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdUpdateRequest).isNotNull(),
                () -> assertThat(ootdUpdateRequest).isExactlyInstanceOf(OotdUpdateRequest.class)
        );
    }

    @DisplayName("OotdUpdateRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdUpdateRequest.getContent()).isEqualTo("내용"),
                () -> assertThat(ootdUpdateRequest.getTop()).isEqualTo("상의"),
                () -> assertThat(ootdUpdateRequest.getBottom()).isEqualTo("하의"),
                () -> assertThat(ootdUpdateRequest.getShoes()).isEqualTo("신발"),
                () -> assertThat(ootdUpdateRequest.getAccessory()).isEqualTo("악세사리"),
                () -> assertThat(ootdUpdateRequest.getHashtags()).contains(new String[]{"해시태그1", "해시태그2"})
        );
    }
}
