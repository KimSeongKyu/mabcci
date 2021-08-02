package com.mabcci.domain.ootd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class OotdRepositoryTest {

    private Ootd ootd;

    @Autowired
    private OotdRepository ootdRepository;

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

    @DisplayName("OotdRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdRepository).isNotNull(),
                () -> assertThat(ootdRepository).isInstanceOf(OotdRepository.class)
        );
    }
}
