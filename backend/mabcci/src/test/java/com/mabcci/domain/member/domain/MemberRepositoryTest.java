package com.mabcci.domain.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@EnableJpaAuditing
@DataJpaTest
class MemberRepositoryTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .email("sample@email.com")
                .password("validPassword")
                .nickname("sample")
                .phone("010-1234-5678")
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();
    }

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("MemberRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        // when and then
        assertAll(
                () -> assertThat(memberRepository).isNotNull(),
                () -> assertThat(memberRepository).isInstanceOf(MemberRepository.class)
        );
    }

    @DisplayName("MemberRepository save 기능 테스트")
    @Test
    void save_test() {
        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.id()).isEqualTo(member.id());
    }

    @DisplayName("MemberRepository findByNickname 기능 테스트")
    @Test
    void findByNickname_test() {
        // given
        testEntityManager.persist(member);

        // when
        memberRepository.findByNickname(member.nickname());

        // then
        assertThat(savedMember.id()).isEqualTo(member.id());
    }

}