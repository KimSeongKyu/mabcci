package com.mabcci.domain.hashtag.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HashtagFindByNicknameContainsResponseTest {

    private HashtagFindByNicknameContainsResponse hashtagFindByNicknameContainsResponse;

    @BeforeEach
    void setUp() {
        hashtagFindByNicknameContainsResponse =
                new HashtagFindByNicknameContainsResponse(List.of("해시태그1", "해시태그2"));
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagFindByNicknameContainsResponse).isNotNull(),
                () -> assertThat(hashtagFindByNicknameContainsResponse)
                        .isExactlyInstanceOf(HashtagFindByNicknameContainsResponse.class)
        );
    }

    @DisplayName("HashtagFindByNicknameContainsResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertThat(hashtagFindByNicknameContainsResponse.hashtags()).contains("해시태그1", "해시태그2");
    }
}
