package com.mabcci.domain.ootd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdTest {

    @DisplayName("Ootd 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final Ootd ootd = Ootd.builder()
                .member(MEMBER)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .build();

        assertAll(
                () -> assertThat(ootd).isNotNull(),
                () -> assertThat(ootd).isExactlyInstanceOf(Ootd.class)
        );
    }
}
