package com.mabcci.domain.ootdLike.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdLikeTest {

    private OotdLike ootdLike;

    @BeforeEach
    void setUp() {
        ootdLike = OotdLike.builder()
                .member(MEMBER)
                .ootd(OOTD)
                .build();
    }

    @DisplayName("OotdLike 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdLike).isNotNull(),
                () -> assertThat(ootdLike).isInstanceOf(OotdLike.class)
        );
    }

    @DisplayName("OotdLike 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdLike ootdLike = new OotdLike();

        assertAll(
                () -> assertThat(ootdLike).isNotNull(),
                () -> assertThat(ootdLike).isInstanceOf(OotdLike.class)
        );
    }
}
