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
}
