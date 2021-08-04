package com.mabcci.domain.hashtag.dto;

import com.mabcci.domain.hashtag.domain.Hashtag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HashtagRegisterResponseTest {

    private HashtagRegisterResponse hashtagRegisterResponse;
    private List<Hashtag> hashtags;

    @BeforeEach
    void setUp() {
        hashtags = new ArrayList<>(List.of(
                Hashtag.builder()
                        .name("해시태그1")
                        .build(),
                Hashtag.builder()
                        .name("해시태그2")
                        .build()
        ));
        hashtagRegisterResponse = new HashtagRegisterResponse(hashtags);
    }

    @DisplayName("HashtagRegisterResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagRegisterResponse).isNotNull(),
                () -> assertThat(hashtagRegisterResponse).isExactlyInstanceOf(HashtagRegisterResponse.class)
        );
    }

    @DisplayName("HashtagRegisterResponse 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(hashtagRegisterResponse.getHashtags()).isEqualTo(hashtags);
    }
}
