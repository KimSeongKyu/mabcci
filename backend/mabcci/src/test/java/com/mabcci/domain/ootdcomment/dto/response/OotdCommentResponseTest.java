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
    private LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        ootdCommentResponse = new OotdCommentResponse("testUrl", NICKNAME, now, now, "내용");
    }

    @DisplayName("OotdCommentResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentResponse).isNotNull(),
                () -> assertThat(ootdCommentResponse).isExactlyInstanceOf(OotdCommentResponse.class)
        );
    }

    @DisplayName("OotdCommentResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdCommentResponse.getMemberPicture()).isEqualTo("testUrl"),
                () -> assertThat(ootdCommentResponse.getMemberNickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdCommentResponse.getCreatedDate()).isEqualTo(now),
                () -> assertThat(ootdCommentResponse.getModifiedDate()).isEqualTo(now),
                () -> assertThat(ootdCommentResponse.getContent()).isEqualTo("내용")
        );
    }
}
