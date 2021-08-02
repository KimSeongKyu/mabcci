package com.mabcci.domain.ootd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdTest {

    private Ootd ootd;

    @BeforeEach
    void setUp() {
        ootd = Ootd.builder()
                .member(MEMBER)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .build();
    }

    @DisplayName("Ootd 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootd).isNotNull(),
                () -> assertThat(ootd).isExactlyInstanceOf(Ootd.class)
        );
    }

    @DisplayName("Ootd 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootd.member()).isEqualTo(MEMBER),
                () -> assertThat(ootd.content()).isEqualTo("content"),
                () -> assertThat(ootd.top()).isEqualTo("top"),
                () -> assertThat(ootd.bottom()).isEqualTo("bottom"),
                () -> assertThat(ootd.shoes()).isEqualTo("shoes"),
                () -> assertThat(ootd.accessory()).isEqualTo("accessory")
        );
    }
}
