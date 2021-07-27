package com.mabcci.domain.auth.domain;

import com.mabcci.domain.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RefreshTokenTest {

    @Mock
    private Member member;

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        // given
        String value = "test.refresh.token";

        // when
        RefreshToken refreshToken = RefreshToken.builder()
                .member(member)
                .refreshToken(value)
                .build();

        // then
        assertThat(refreshToken).isNotNull();
    }
}
