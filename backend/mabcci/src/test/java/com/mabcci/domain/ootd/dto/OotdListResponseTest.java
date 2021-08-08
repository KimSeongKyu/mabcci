package com.mabcci.domain.ootd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdListResponseTest {

    private OotdListResponse ootdListResponse;
    private List<OotdResponse> ootdResponses;

    @BeforeEach
    void setUp() {
        ootdResponses = new ArrayList<>(List.of(
                new OotdResponse(1L, "닉네임1", "url/name1.png",
                        new ArrayList<>(List.of("해시태그1", "해시태그2")), 10L),
                new OotdResponse(1L, "닉네임2", "url/name2.png",
                        new ArrayList<>(List.of("해시태그1", "해시태그3")), 20L)
        ));
        ootdListResponse = new OotdListResponse(ootdResponses, 1L);
    }

    @DisplayName("OotdListResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdListResponse).isNotNull(),
                () -> assertThat(ootdListResponse).isExactlyInstanceOf(OotdListResponse.class)
        );
    }
}
