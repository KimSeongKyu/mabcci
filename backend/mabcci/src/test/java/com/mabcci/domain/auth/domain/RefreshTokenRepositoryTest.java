package com.mabcci.domain.auth.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@EnableJpaAuditing
@DataJpaTest
public class RefreshTokenRepositoryTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .email("sample@email.com")
                .password("validPassword")
                .nickname("sample")
                .phone("010-1234-5678")
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();

        memberRepository.save(member);
    }

    @DisplayName(value = "refresh token 유효성 검증 테스트")
    @ParameterizedTest(name = "{index}. refresh token: {0}")
    @NullAndEmptySource
    public void validateRefreshTokenTest(String value) {
        // given
        RefreshToken refreshToken = RefreshToken.builder()
                .member(member)
                .refreshToken(value)
                .build();

        // when and then
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(() -> {
            refreshTokenRepository.save(refreshToken);
            testEntityManager.flush();
        });
    }

}
