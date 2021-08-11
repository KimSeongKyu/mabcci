package com.mabcci.domain.ootdcomment.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdCommentUpdateRequestTest {

    private OotdCommentUpdateRequest ootdCommentUpdateRequest;

    @BeforeEach
    void setUp() {
        ootdCommentUpdateRequest = new OotdCommentUpdateRequest("수정된 내용");
    }

    @DisplayName("OotdCommentUpdateRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentUpdateRequest).isNotNull(),
                () -> assertThat(ootdCommentUpdateRequest).isExactlyInstanceOf(OotdCommentUpdateRequest.class)
        );
    }

    @DisplayName("OotdCommentUpdateRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(ootdCommentUpdateRequest.getContent()).isEqualTo("수정된 내용");
    }
}
