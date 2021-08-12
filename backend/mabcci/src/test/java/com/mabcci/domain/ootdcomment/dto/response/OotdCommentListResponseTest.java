package com.mabcci.domain.ootdcomment.dto.response;

import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdCommentListResponseTest {

    private OotdCommentListResponse ootdCommentListResponse;
    private OotdCommentResponse firstOotdCommentResponse;
    private OotdCommentResponse secondOotdCommentResponse;

    @BeforeEach
    void setUp() {
        firstOotdCommentResponse = new OotdCommentResponse("testUrl1", Nickname.of("닉네임1"),
                now(), now(), "내용1", new ArrayList<>());
        secondOotdCommentResponse = new OotdCommentResponse("testUrl2", Nickname.of("닉네임2"),
                now(), now(), "내용2", new ArrayList<>());
        ootdCommentListResponse = new OotdCommentListResponse(List.of(firstOotdCommentResponse, secondOotdCommentResponse));
    }

    @DisplayName("OotdCommentListResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdCommentListResponse).isNotNull(),
                () -> assertThat(ootdCommentListResponse).isExactlyInstanceOf(OotdCommentListResponse.class)
        );
    }

    @DisplayName("OotdCommentListResponse 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(ootdCommentListResponse.getOotdCommentResponses()).containsAll(firstOotdCommentResponse, secondOotdCommentResponse);
    }
}
