package com.mabcci.domain.auth.common;

import com.mabcci.domain.auth.domain.vo.ClaimType;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.domain.vo.JwtTokenType;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.Key;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JwtUtilTest {

    static Stream<Arguments> provide_claim_types_for_create_claim_test() {
        return Stream.of(
                Arguments.of(ClaimType.HEADER, new String[]{"typ", "alg"}),
                Arguments.of(ClaimType.PAYLOAD, new String[]{"iss", "sub", "aud"})
        );
    }

    static Stream<Arguments> provide_token_types_for_tests_about_token() {
        return Arrays.stream(JwtTokenType.values())
                .map(tokenType -> Arguments.of(tokenType));
    }

    @DisplayName("JwtUtil 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final JwtUtil jwtUtil = new JwtUtil();

        assertAll(
                () -> assertThat(jwtUtil).isNotNull(),
                () -> assertThat(jwtUtil).isExactlyInstanceOf(JwtUtil.class)
        );
    }

    @DisplayName("JwtUtil 인스턴스 claim 생성 기능 테스트")
    @ParameterizedTest(name = "{index}. Claim Type: {0} | keys: {1}")
    @MethodSource("provide_claim_types_for_create_claim_test")
    void create_claim_test(final ClaimType claimType, final String[] expectedKeys) {
        final JwtUtil jwtUtil = new JwtUtil();
        final Map<String, Object> claim = jwtUtil.createClaim(claimType);

        assertThat(claim.keySet()).contains(expectedKeys);
    }

    @DisplayName("JwtUtil 인스턴스 payload 생성 기능 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource("provide_token_types_for_tests_about_token")
    void create_payload_test(final JwtTokenType jwtTokenType) {
        final JwtUtil jwtUtil = new JwtUtil();
        final String[] expectedKeys = new String[]{"iss", "sub", "aud", "exp", "nbf", "iat", "email", "nickname", "role"};
        final Map<String, Object> payload = jwtUtil.createPayload(jwtTokenType, MEMBER);

        assertThat(payload.keySet()).contains(expectedKeys);
    }

    @DisplayName("JwtUtil 인스턴스 secretKey 생성 기능 테스트")
    @Test
    void create_secret_key_test() {
        final JwtUtil jwtUtil = new JwtUtil();
        final Key key = jwtUtil.createSecretKey();

        assertThat(key.getAlgorithm()).isEqualTo(SignatureAlgorithm.HS256.getJcaName());
    }

    @DisplayName("JwtUtil 인스턴스 token 생성 기능 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource("provide_token_types_for_tests_about_token")
    void create_token_test(final JwtTokenType jwtTokenType) {
        final JwtUtil jwtUtil = new JwtUtil();
        final JwtToken jwtToken = jwtUtil.createToken(jwtTokenType, MEMBER);

        Arrays.stream(jwtToken.jwtToken().split("."))
                .forEach(jwtTokenSplitByComma -> assertThat(jwtTokenSplitByComma).isBase64());
    }

    @DisplayName("JwtUtil 인스턴스 token 유효성 검증 기능 테스트")
    @Test
    void is_valid_token_test() {
        final JwtUtil jwtUtil = new JwtUtil();
        final JwtToken accessToken = jwtUtil.createToken(JwtTokenType.ACCESS_TOKEN, MEMBER);
        final JwtToken refreshToken = jwtUtil.createToken(JwtTokenType.REFRESH_TOKEN, MEMBER);
        final JwtToken invalidToken = JwtToken.of("invalid.test.token");

        assertAll(
                () -> assertThat(jwtUtil.isValidToken(accessToken)).isTrue(),
                () -> assertThat(jwtUtil.isValidToken(refreshToken)).isTrue(),
                () -> assertThat(jwtUtil.isValidToken(invalidToken)).isFalse()
        );
    }
}
