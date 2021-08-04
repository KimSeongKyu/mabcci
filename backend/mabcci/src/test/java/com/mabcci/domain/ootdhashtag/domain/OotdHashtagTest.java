package com.mabcci.domain.ootdhashtag.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.hashtag.domain.HashtagTest.HASHTAG;
import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdHashtagTest {

    private OotdHashtag ootdHashtag;

    @BeforeEach
    void setUp() {
        ootdHashtag = OotdHashtag.builder()
                .ootd(OOTD)
                .hashtag(HASHTAG)
                .build();
    }

    @DisplayName("OotdHashtag 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdHashtag).isNotNull(),
                () -> assertThat(ootdHashtag).isExactlyInstanceOf(OotdHashtag.class)
        );
    }

    @DisplayName("OotdHashtag 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdHashtag ootdHashtag = new OotdHashtag();

        assertAll(
                () -> assertThat(ootdHashtag).isNotNull(),
                () -> assertThat(ootdHashtag).isExactlyInstanceOf(OotdHashtag.class)
        );
    }
}
