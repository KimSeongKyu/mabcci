package com.mabcci.domain.ootdcomment.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdCommentResponseTest {

    private OotdCommentResponse ootdCommentResponse;

    @BeforeEach
    void setUp() {
        ootdCommentResponse = new OotdCommentResponse("testUrl", NICKNAME, LocalDateTime.now(), LocalDateTime.now(), "내용");
    }

    @DisplayName("OotdCommentResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentResponse).isNotNull(),
                () -> assertThat(ootdCommentResponse).isExactlyInstanceOf(OotdCommentResponse.class)
        );
    }
}
