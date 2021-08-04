package com.mabcci.domain.ootdhashtag.dto;

import com.mabcci.domain.hashtag.domain.Hashtag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mabcci.domain.hashtag.domain.HashtagTest.HASHTAG;
import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdHashtagRegisterRequestTest {

    private OotdHashtagRegisterRequest ootdHashtagRegisterRequest;
    private List<Hashtag> hashtags;

    @BeforeEach
    void setUp() {
        hashtags = new ArrayList<>(List.of(HASHTAG, HASHTAG));
        ootdHashtagRegisterRequest = new OotdHashtagRegisterRequest(OOTD, hashtags);
    }

    @DisplayName("OotdHashtagRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdHashtagRegisterRequest).isNotNull(),
                () -> assertThat(ootdHashtagRegisterRequest).isExactlyInstanceOf(OotdHashtagRegisterRequest.class)
        );
    }

    @DisplayName("OotdHashtagRegisterRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdHashtagRegisterRequest.getOotd()).isEqualTo(OOTD),
                () -> assertThat(ootdHashtagRegisterRequest.getHashtags()).isEqualTo(hashtags)
        );
    }
}
