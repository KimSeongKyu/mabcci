package com.mabcci.domain.like.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LikeTest {

    private Like like;

    @BeforeEach
    void setUp() {
        like = new Like(MEMBER);
    }

    @DisplayName("Like 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(like).isNotNull(),
                () -> assertThat(like).isExactlyInstanceOf(Like.class)
        );
    }

    @DisplayName("Like 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Like like = new Like();

        assertAll(
                () -> assertThat(like).isNotNull(),
                () -> assertThat(like).isExactlyInstanceOf(Like.class)
        );
    }
}
